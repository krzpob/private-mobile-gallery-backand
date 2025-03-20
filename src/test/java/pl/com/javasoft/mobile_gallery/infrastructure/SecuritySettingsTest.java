package pl.com.javasoft.mobile_gallery.infrastructure;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.hibernate.mapping.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import jakarta.transaction.Transactional;
import pl.com.javasoft.mobile_gallery.domain.model.Authorities;
import pl.com.javasoft.mobile_gallery.domain.model.Customer;
import pl.com.javasoft.mobile_gallery.domain.model.User;
import pl.com.javasoft.mobile_gallery.domain.model.Authorities.AuthorityType;
import pl.com.javasoft.mobile_gallery.domain.repository.AuthorityRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecuritySettingsTest {
    
    TestRestTemplate testRestTemplate;
    URI base;
    @LocalServerPort int port;

    @MockitoBean
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @BeforeEach    
    public void setup() throws MalformedURLException, URISyntaxException{
        testRestTemplate = new TestRestTemplate("user", "password");
        base = new URI("http://localhost:"+port+"/customers");
        
        User user = new User("user", "password");
        
        Authorities authorities = new Authorities();
        authorities.setEmail("user");
        authorities.setAuthority(AuthorityType.USER);
        
        user.getAuthorities().add(authorities);
        BDDMockito.given(userRepository.findByEmail(eq("user"))).willReturn(user);

    }

    @Test
    public void whenLoggedUserRequestsListOfCustomersThenSuccess(){        
        ParameterizedTypeReference<List<Customer>> typeRef = new ParameterizedTypeReference<List<Customer>>() {};
        ResponseEntity<List<Customer>> response = testRestTemplate.withBasicAuth("user", "password").exchange(base, HttpMethod.GET, HttpEntity.EMPTY,typeRef);
        BDDAssertions.then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
