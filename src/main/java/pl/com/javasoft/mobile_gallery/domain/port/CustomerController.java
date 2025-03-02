package pl.com.javasoft.mobile_gallery.domain.port;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Customer;
import pl.com.javasoft.mobile_gallery.domain.port.dto.CustomerCreateCommand;
import pl.com.javasoft.mobile_gallery.domain.service.CustomerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer postMethodName(@RequestBody CustomerCreateCommand customerCommand) {
        return customerService.addCustomer(customerCommand.getEmail(),customerCommand.getPassword());
    }

    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customerService.fetchAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
    }

}
