package pl.com.javasoft.mobile_gallery.infrastructure.web;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.application.base.DispatchableHandler;
import pl.com.javasoft.mobile_gallery.application.command.CreateGalleryCommand;

import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController(value = "/api/v1/gallery")
@RequiredArgsConstructor
public class GalleryController {
    private final DispatchableHandler dispatchableHandler;

    @PostMapping()
    public Map<String, String> createGallery(@RequestBody CreateGalleryCommand createGalleryCommand){
        Long galleryId = dispatchableHandler.dispatch(createGalleryCommand);
        if (galleryId == null) {
            throw new IllegalArgumentException("Gallery creation failed");
        }

        return Map.of("galleryId", String.valueOf(galleryId));

    } 
    
    
}
