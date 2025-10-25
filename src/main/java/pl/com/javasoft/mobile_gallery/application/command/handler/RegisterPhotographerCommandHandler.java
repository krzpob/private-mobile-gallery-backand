package pl.com.javasoft.mobile_gallery.application.command.handler;

import org.springframework.security.core.parameters.P;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.application.base.CommandHandler;
import pl.com.javasoft.mobile_gallery.application.command.RegisterPhotographerCommand;
import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;
import pl.com.javasoft.mobile_gallery.infrastructure.api.user.UserRegistrationRequest;
import pl.com.javasoft.mobile_gallery.infrastructure.service.UserManagementService;


@RequiredArgsConstructor
public class RegisterPhotographerCommandHandler implements CommandHandler<RegisterPhotographerCommand, Long> {
    
    private final PhotographerRepository photographerRepository;
    private final UserManagementService userManagementService;
    
    @Override
    @Transactional
    public Long handle(RegisterPhotographerCommand command) {
        userManagementService.registerPhotograph(new UserRegistrationRequest(command.emailAddress(), command.name(), command.password()));        
        Photographer photographer = Photographer.builder()
                .email(command.emailAddress())
                .name(command.name())
                .build();
        var id = photographerRepository.save(photographer);
        
        return id;
    }
    
}
