package pl.com.javasoft.mobile_gallery.domain.port;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.com.javasoft.mobile_gallery.domain.model.Session;
import pl.com.javasoft.mobile_gallery.domain.model.User;
import pl.com.javasoft.mobile_gallery.domain.port.dto.BasicPhoto;
import pl.com.javasoft.mobile_gallery.domain.port.dto.CreateSessionCommand;
import pl.com.javasoft.mobile_gallery.domain.port.dto.SessionDTO;
import pl.com.javasoft.mobile_gallery.domain.service.SessionService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@Slf4j
@RestController
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/customer/{id}/sessions")
    public List<SessionDTO> findCustomerSessions(@PathVariable Long id){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("User {}",(User)auth.getPrincipal());  
        return sessionService.getSessionsByCustomerId(id).stream().map(
            session->SessionDTO.builder()
            .withId(session.getId())
            .withName(session.getName())
            .withSessionDate(session.getSessionDate())
            .build()
            ).toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customer/{id}/sessions")
    public Session postMethodName(@PathVariable Long id,@RequestBody CreateSessionCommand createSessionCommand) {
        
        return sessionService.create(id,createSessionCommand);
    }

    @GetMapping("/sessions/{id}")
    public SessionDTO getSession(@PathVariable Long id) {
        var session = sessionService.getSession(id);
        return SessionDTO.builder()
            .withId(session.getId())
            .withName(session.getName())
            .withPhotos(session.getPhotos().stream().map(
                photo-> new BasicPhoto(photo.getId(),photo.isFavorit(),photo.getSessionId())
            ).toList())
            .withSessionDate(session.getSessionDate())
            .build();
    }
    
    
    
}
