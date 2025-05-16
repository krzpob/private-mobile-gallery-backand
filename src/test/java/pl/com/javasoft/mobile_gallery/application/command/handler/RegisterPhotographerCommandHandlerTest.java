package pl.com.javasoft.mobile_gallery.application.command.handler;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.BDDMockito.*;

import org.assertj.core.api.BDDAssertions;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import pl.com.javasoft.mobile_gallery.application.command.RegisterPhotographerCommand;
import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;
import pl.com.javasoft.mobile_gallery.domain.service.UserRegistrationService;

public class RegisterPhotographerCommandHandlerTest {

    private PhotographerRepository photographerRepository;
    private UserRegistrationService userRegistrationService;
    private RegisterPhotographerCommandHandler handler;

    @BeforeEach
    void setUp() {
        photographerRepository = mock(PhotographerRepository.class);
        userRegistrationService = mock(UserRegistrationService.class);
        handler = new RegisterPhotographerCommandHandler(photographerRepository, userRegistrationService);
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
        then(userRegistrationService).should().registerUser(
                eq("anna.kowalska@example.com"),
                eq("superTajneHaslo123"),
                eq("PHOTOGRAPHER")
        );

        ArgumentCaptor<Photographer> captor = ArgumentCaptor.forClass(Photographer.class);
        then(photographerRepository).should().save(captor.capture());

        Photographer savedPhotographer = captor.getValue();
        BDDAssertions.then(savedPhotographer).isNotNull();
        BDDAssertions.then(savedPhotographer.getEmail()).isEqualTo("anna.kowalska@example.com");
        BDDAssertions.then(savedPhotographer.getName()).isEqualTo("Anna Kowalska");
    }

    @Test
    @DisplayName("Powinien rzucić wyjątek i NIE zapisać fotografa, gdy rejestracja użytkownika się nie powiedzie")
    void shouldThrowExceptionAndNotSavePhotographerWhenUserRegistrationFails() {
        // given
        RegisterPhotographerCommand command = new RegisterPhotographerCommand(
                "jan.nowak@example.com",
                "niebezpieczneHaslo456",
                "Jan Nowak"
        );

        doThrow(new IllegalStateException("Błąd rejestracji")).when(userRegistrationService)
                .registerUser(anyString(), anyString(), anyString());

        // when & then
        
         BDDAssertions.thenThrownBy(() -> handler.handle(command))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Błąd rejestracji");


        then(userRegistrationService).should().registerUser(
                eq("jan.nowak@example.com"),
                eq("niebezpieczneHaslo456"),
                eq("PHOTOGRAPHER")
        );

        BDDMockito.then(photographerRepository)
                .shouldHaveNoInteractions();
    }
}
