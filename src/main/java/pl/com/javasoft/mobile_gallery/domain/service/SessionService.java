package pl.com.javasoft.mobile_gallery.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Customer;
import pl.com.javasoft.mobile_gallery.domain.model.Session;
import pl.com.javasoft.mobile_gallery.domain.port.dto.CreateSessionCommand;
import pl.com.javasoft.mobile_gallery.domain.repository.CustomerRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.SessionRepository;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final CustomerRepository customerRepository;
    private final SessionRepository sessionRepository;
    
    public List<Session> getSessionsByCustomerId(Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new RestClientResponseException(null, HttpStatus.NOT_FOUND, null, null, null, null);
        }

        return sessionRepository.findByCustomer(customer.get());
        
    }

    public Session create(Integer id,CreateSessionCommand createSessionCommand) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new RestClientResponseException(null, HttpStatus.NOT_FOUND, null, null, null, null);
        }
        return sessionRepository.save(new Session(createSessionCommand.getName(), createSessionCommand.getSessDateTime(), customer.get()));
    }

    public Session getSession(Long id) {
        var result = sessionRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        } else {
            throw new RestClientResponseException(null, HttpStatus.NOT_FOUND, null, null, null, null);
        }
    }
}
