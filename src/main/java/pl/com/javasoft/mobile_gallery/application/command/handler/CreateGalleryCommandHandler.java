package pl.com.javasoft.mobile_gallery.application.command.handler;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.com.javasoft.mobile_gallery.application.base.CommandHandler;
import pl.com.javasoft.mobile_gallery.application.base.Dispatchable;
import pl.com.javasoft.mobile_gallery.application.command.CreateGalleryCommand;
import pl.com.javasoft.mobile_gallery.domain.model.Gallery;
import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.domain.repository.GalleryRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotographerRepository;

@Service
@AllArgsConstructor
public class CreateGalleryCommandHandler implements CommandHandler<CreateGalleryCommand, Long> {
    private final PhotographerRepository photographerRepository;
    private final GalleryRepository galleryRepository;

    public Long handle(CreateGalleryCommand command) {
        Photographer photographer = photographerRepository.findById(command.photographerId());
        if (photographer == null) {
            throw new IllegalArgumentException("Photographer not found");
        }

        Gallery gallery = Gallery.builder()
                .name(command.name())
                .owner(photographer)
                .build();
        var g = galleryRepository.save(gallery);
        return g.getId();
    }

    
}