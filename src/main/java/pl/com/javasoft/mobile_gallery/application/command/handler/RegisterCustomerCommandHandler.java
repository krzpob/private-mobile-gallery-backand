package pl.com.javasoft.mobile_gallery.application.command.handler;

import java.util.Collections;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.application.base.CommandHandler;
import pl.com.javasoft.mobile_gallery.application.command.RegisterCustomerCommand;
import pl.com.javasoft.mobile_gallery.domain.model.Client;
import pl.com.javasoft.mobile_gallery.domain.repository.ClientRepository;
import pl.com.javasoft.mobile_gallery.domain.service.UserRegistrationService;

@Service
@RequiredArgsConstructor
public class RegisterCustomerCommandHandler implements CommandHandler<RegisterCustomerCommand, Long> {
    private final UserRegistrationService userRegistrationService; 
    private final ClientRepository clientRepository; 

    @Override
    public Long handle(RegisterCustomerCommand command) {
        userRegistrationService.registerUser(command.getEmail(), command.getPassword(), "CUSTOMER");
        var client = clientRepository.save(new Client(command.getEmail()));
        return client.getId(); 
    }

}
