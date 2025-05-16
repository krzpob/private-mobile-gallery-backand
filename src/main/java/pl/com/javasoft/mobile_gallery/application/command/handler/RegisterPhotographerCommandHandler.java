package pl.com.javasoft.mobile_gallery.application.command.handler;

import org.springframework.security.core.parameters.P;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.application.base.CommandHandler;
import pl.com.javasoft.mobile_gallery.application.command.RegisterPhotographerCommand;
import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;
import pl.com.javasoft.mobile_gallery.domain.service.UserRegistrationService;


@RequiredArgsConstructor
public class RegisterPhotographerCommandHandler implements CommandHandler<RegisterPhotographerCommand, Long> {
    
    private final PhotographerRepository photographerRepository;
    private final UserRegistrationService userRegistrationService; 

    @Override
    @Transactional
    public Long handle(RegisterPhotographerCommand command) {
        userRegistrationService.registerUser(command.emailAddress(), command.password(), "PHOTOGRAPHER");
        
        Photographer photographer = Photographer.builder()
                .email(command.emailAddress())
                .name(command.name())
                .build();
        var id = photographerRepository.save(photographer);
        
        return id;
    }
    
}
