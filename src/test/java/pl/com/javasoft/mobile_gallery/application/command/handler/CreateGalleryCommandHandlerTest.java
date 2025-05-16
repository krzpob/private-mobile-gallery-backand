package pl.com.javasoft.mobile_gallery.application.command.handler;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.com.javasoft.mobile_gallery.application.command.CreateGalleryCommand;
import pl.com.javasoft.mobile_gallery.domain.model.Gallery;
import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.domain.repository.GalleryRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;


@ExtendWith(MockitoExtension.class)
public class CreateGalleryCommandHandlerTest {

    private PhotographerRepository photographerRepository;
    private GalleryRepository galleryRepository;
    private CreateGalleryCommandHandler createGalleryCommandHandler;  

    @BeforeEach
    void setup(){
        photographerRepository = Mockito.mock(PhotographerRepository.class);
        galleryRepository = Mockito.mock(GalleryRepository.class);
        createGalleryCommandHandler = new CreateGalleryCommandHandler(photographerRepository, galleryRepository);
    }

    @Test
    void shouldCreateGalleryForExistingPhotograper() {
        // Given
        Long photographerId = 1L;
        String galleryName = "My Gallery";
        CreateGalleryCommand command = new CreateGalleryCommand(photographerId, galleryName);
        Photographer photographer = Photographer.builder().id(photographerId).build();
        BDDMockito.given(photographerRepository.findById(photographerId)).willReturn(photographer);
        BDDMockito.given(galleryRepository.save(Mockito.any())).willAnswer(invocation -> {
            Gallery gallery = invocation.getArgument(0);
            
            return Gallery.builder()
                    .id(1L)
                    .name(gallery.getName())
                    .owner(gallery.getOwner())
                    .build();
        });  

        // When
        Long galleryId = createGalleryCommandHandler.handle(command);

        // Then
        BDDMockito.then(photographerRepository).should().findById(photographerId);
        BDDMockito.then(galleryRepository).should().save(Mockito.argThat(gallery -> 
            gallery.getName().equals(galleryName) && gallery.getOwner().equals(photographer)
        ));
        BDDAssertions.then(galleryId).isNotNull();
    }

    @Test
    void shouldThrowExceptionForNonExistingPhotographer() {
        // Given
        Long photographerId = 1L;
        String galleryName = "My Gallery";
        CreateGalleryCommand command = new CreateGalleryCommand(photographerId, galleryName);
        BDDMockito.given(photographerRepository.findById(photographerId)).willReturn(null);

        // When & Then
        BDDAssertions.thenThrownBy(() -> createGalleryCommandHandler.handle(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Photographer not found");
        BDDMockito.then(photographerRepository).should().findById(photographerId);
    }
}
