package pl.com.javasoft.mobile_gallery.application.command.handler;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.*;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;
import pl.com.javasoft.mobile_gallery.application.command.RegisterPhotographerCommand;
import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;
import pl.com.javasoft.mobile_gallery.infrastructure.api.user.UserRegistrationRequest;
import pl.com.javasoft.mobile_gallery.infrastructure.service.UserManagementService;

public class RegisterPhotographerCommandHandlerTest {

    private PhotographerRepository photographerRepository;
    private RegisterPhotographerCommandHandler handler;
    private UserManagementService userManagementService;

    @BeforeEach
    void setUp() {
        photographerRepository = mock(PhotographerRepository.class);
        userManagementService = mock(UserManagementService.class);
        handler = new RegisterPhotographerCommandHandler(photographerRepository, userManagementService);
    }

     @Test
    @DisplayName("Powinien zarejestrować użytkownika i utworzyć nowego fotografa")
    void shouldRegisterUserAndCreatePhotographer() {
        // given
        RegisterPhotographerCommand command = new RegisterPhotographerCommand(
                "anna.kowalska@example.com",
                "superTajneHaslo123",
                "Anna Kowalska"
        );

        // when
        handler.handle(command);

        // then
        then(userManagementService).should().registerPhotograph(any(UserRegistrationRequest.class));
                
        ArgumentCaptor<Photographer> captor = ArgumentCaptor.forClass(Photographer.class);
        then(photographerRepository).should().save(captor.capture());

        Photographer savedPhotographer = captor.getValue();
        BDDAssertions.then(savedPhotographer).isNotNull();
        BDDAssertions.then(savedPhotographer.getEmail()).isEqualTo("anna.kowalska@example.com");
        BDDAssertions.then(savedPhotographer.getName()).isEqualTo("Anna Kowalska");
    }

}
