package pl.com.javasoft.mobile_gallery.infrastructure.api;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.com.javasoft.mobile_gallery.application.base.DispatchableHandler;
import pl.com.javasoft.mobile_gallery.application.command.RegisterCustomerCommand;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientController {

    private final DispatchableHandler dispatchableHandler;

   
    @PostMapping("/api/v1/client")
    public Map<String, String> registerClient(RegisterCustomerCommand command) {
        Long clientId = dispatchableHandler.dispatch(command);
        if (clientId == null) {
            throw new IllegalArgumentException("Client registration failed");
        }
        log.info("Client registered with ID: {} and email: {}", clientId, command.getEmail());
        return Map.of("clientId", String.valueOf(clientId), "email", command.getEmail());
    }
}
