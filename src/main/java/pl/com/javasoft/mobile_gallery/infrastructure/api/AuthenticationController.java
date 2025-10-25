package pl.com.javasoft.mobile_gallery.infrastructure.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AuthenticationController {
    
    

    @GetMapping("/authentication")
    public String authenticate(@RequestParam String username, @RequestParam String password) {
        // Implement authentication logic here
        return new String();
    }
    
    
}
