package pl.com.javasoft.mobile_gallery.domain.port;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Session;
import pl.com.javasoft.mobile_gallery.domain.port.dto.CreateSessionCommand;
import pl.com.javasoft.mobile_gallery.domain.service.SessionService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/customer/{id}/sessions")
    public List<Session> findCustomerSessions(@PathVariable Integer id){
        return sessionService.getSessionsByCustomerId(id);
    }

    @PostMapping("/customer/{id}/sessions")
    public Session postMethodName(@PathVariable Integer id,@RequestBody CreateSessionCommand createSessionCommand) {
        
        return sessionService.create(createSessionCommand);;
    }
    
    
}
