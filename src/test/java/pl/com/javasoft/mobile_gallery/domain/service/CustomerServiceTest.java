package pl.com.javasoft.mobile_gallery.domain.service;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pl.com.javasoft.mobile_gallery.domain.repository.CustomerRepository;

@DataJpaTest()
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    
    private CustomerService service;

    @BeforeEach
    void setup(){
        service = new CustomerService(customerRepository);
    }

    @Test
    void shouldAddCustomer(){
        var customer = service.addCustomer("test@test.pl", "test");
        BDDAssertions.then(customer.checkPassword("test")).isTrue();
    }

}
