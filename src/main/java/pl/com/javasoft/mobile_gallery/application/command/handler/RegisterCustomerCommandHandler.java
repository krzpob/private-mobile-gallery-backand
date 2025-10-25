package pl.com.javasoft.mobile_gallery.application.command.handler;

import java.util.Collections;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.application.base.CommandHandler;
import pl.com.javasoft.mobile_gallery.application.command.RegisterCustomerCommand;
import pl.com.javasoft.mobile_gallery.domain.model.Client;
import pl.com.javasoft.mobile_gallery.domain.repository.ClientRepository;
import pl.com.javasoft.mobile_gallery.infrastructure.api.user.UserRegistrationRequest;
import pl.com.javasoft.mobile_gallery.infrastructure.service.UserManagementService;

@Service
@RequiredArgsConstructor
public class RegisterCustomerCommandHandler implements CommandHandler<RegisterCustomerCommand, Long> {
    private final ClientRepository clientRepository; 
    private final UserManagementService userManagementService;

    @Override
    public Long handle(RegisterCustomerCommand command) {
        userManagementService.registerUser(new UserRegistrationRequest(command.getEmail(), command.getEmail(), command.getPassword()));        
        var client = clientRepository.save(new Client(command.getEmail()));
        return client.getId(); 
    }

}
