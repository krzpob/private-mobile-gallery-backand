package pl.com.javasoft.mobile_gallery.domain.port;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.com.javasoft.mobile_gallery.domain.model.Customer;
import pl.com.javasoft.mobile_gallery.domain.model.Authorities.AuthorityType;
import pl.com.javasoft.mobile_gallery.domain.port.dto.CustomerCreateCommand;
import pl.com.javasoft.mobile_gallery.domain.service.CustomerService;
import pl.com.javasoft.mobile_gallery.domain.service.MyUserPrincipal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Log4j2
@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()    
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> postMethodName(@RequestBody CustomerCreateCommand customerCommand) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth==null || auth.getAuthorities().stream().noneMatch(a->a.getAuthority().equals(AuthorityType.PHOTOGRAPHER.name()))){
            log.error("Unautorized access: {}", auth.getCredentials());
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok().body(
            customerService.addCustomer(customerCommand.getEmail(),customerCommand.getPassword(), (MyUserPrincipal)auth.getPrincipal())
        );
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_PHOTOGRAPHER')")
    public List<Customer> getAllCustomers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ADMIN"))){
            return customerService.fetchAll();
        }
        
        return customerService.fetchAllOwnedBy((MyUserPrincipal )auth.getPrincipal());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCustomer(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        customerService.deleteCustomer(id, (MyUserPrincipal )auth.getPrincipal());
    }

}
