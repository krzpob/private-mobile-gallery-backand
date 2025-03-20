package pl.com.javasoft.mobile_gallery.domain.service;

import java.time.LocalDateTime;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.domain.port.dto.CreateSessionCommand;
import pl.com.javasoft.mobile_gallery.domain.repository.CustomerRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.SessionRepository;

@DataJpaTest()
public class SessionServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    private SessionService sessionService;
    private CustomerService customerService;

    @BeforeEach
    void setup(){
        sessionService = new SessionService(customerRepository, sessionRepository);
        customerService = new CustomerService(customerRepository, photographerRepository);
    }

    @Test
    void shouldCreateSessionForExistingCustomer() {
        //given
         var photographer = new Photographer("kontakt@example.com","password","www.example.com");
        photographerRepository.saveAndFlush(photographer);
        var customer = customerService.addCustomer("test@test.pl", "testtest1234",new MyUserPrincipal(photographer));
        //when
        sessionService.create(customer.getId(), new CreateSessionCommand("test", LocalDateTime.now()));
        //then
        var session = sessionRepository.findByCustomer(customer);
        BDDAssertions.then(session.isEmpty()).isFalse();
        BDDAssertions.then(session).hasSize(1);
        BDDAssertions.then(session.get(0)).isNotNull();
        BDDAssertions.then(session.get(0).getName()).isEqualTo("test");
    }
}
