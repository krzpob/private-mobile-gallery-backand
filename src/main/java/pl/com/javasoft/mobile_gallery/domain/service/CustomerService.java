package pl.com.javasoft.mobile_gallery.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Customer;
import pl.com.javasoft.mobile_gallery.domain.repository.CustomerRepository;


@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    
    public Customer addCustomer(String email, String password){
        var customer = new Customer(email,  password);
        return customerRepository.save(customer);
    }

    public List<Customer> fetchAll() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            return;
        }
        customerRepository.deleteById(id);
    }
}
