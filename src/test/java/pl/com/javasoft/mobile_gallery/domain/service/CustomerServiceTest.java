package pl.com.javasoft.mobile_gallery.domain.service;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.transaction.Transactional;
import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.domain.repository.CustomerRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;

@DataJpaTest()
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    
    private CustomerService service;

    @BeforeEach
    void setup(){
        service = new CustomerService(customerRepository, photographerRepository);
    }

    @Test
    @Transactional
    void shouldAddCustomer(){

        var photographer = new Photographer("kontakt@example.com","password","www.example.com");
        photographerRepository.saveAndFlush(photographer);
        var customer = service.addCustomer("test@test.pl", "test", new MyUserPrincipal(photographer));
        BDDAssertions.then(customer.getEmail()).isEqualTo("test@test.pl");
        
    }

}
