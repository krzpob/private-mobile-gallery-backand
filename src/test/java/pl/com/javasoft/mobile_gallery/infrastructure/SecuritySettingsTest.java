package pl.com.javasoft.mobile_gallery.infrastructure;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.hibernate.mapping.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pl.com.javasoft.mobile_gallery.domain.model.Customer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecuritySettingsTest {
    
    TestRestTemplate testRestTemplate;
    URI base;
    @LocalServerPort int port;

    @BeforeEach
    public void setup() throws MalformedURLException, URISyntaxException{
        testRestTemplate = new TestRestTemplate("user", "password");
        base = new URI("http://localhost:"+port+"/customers");
    }

    @Test
    public void whenLoggedUserRequestsListOfCustomersThenSuccess(){
          ParameterizedTypeReference<List<Customer>> typeRef = new ParameterizedTypeReference<List<Customer>>() {};
          ResponseEntity<List<Customer>> response = testRestTemplate.exchange(base, HttpMethod.GET, HttpEntity.EMPTY,typeRef);
          BDDAssertions.then(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
