package pl.com.javasoft.mobile_gallery.domain.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.com.javasoft.mobile_gallery.domain.model.Customer;
import pl.com.javasoft.mobile_gallery.domain.repository.CustomerRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;


@RequiredArgsConstructor
@Service
@Log4j2
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PhotographerRepository photographerRepository;
    
    public Customer addCustomer(String email, String password, MyUserPrincipal myUserPrincipal){
        var photographOpt = photographerRepository.findOneByEmail(myUserPrincipal.getUsername());
        
        var customer = new Customer(email,  password, photographOpt.get());
        
        return customerRepository.save(customer);
    }

    public List<Customer> fetchAll() {
        return customerRepository.findAll();
    }

    public List<Customer> fetchAllOwnedBy(MyUserPrincipal  principal){
        var photographerOpt = photographerRepository.findOneByEmail(principal.getUsername());
        if(photographerOpt.isEmpty()){
            return Collections.emptyList();
        }

        return new ArrayList<>(photographerOpt.get().getCustomers());
    }

    public void deleteCustomer(Long id, MyUserPrincipal user) {
        Optional<Customer> customer = customerRepository.findById(id);
        log.info("user: {}",user.getUsername());
        if(customer.isEmpty()){
            return;
        }
        customerRepository.deleteById(id);
    }

    public Optional<Customer> findOneById(Long id){
        return customerRepository.findById(id);
    }
}
