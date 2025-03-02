package pl.com.javasoft.mobile_gallery.domain.port;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Photo;
import pl.com.javasoft.mobile_gallery.domain.port.dto.BasicPhoto;
import pl.com.javasoft.mobile_gallery.domain.service.PhotoService;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
public class PhotoController {
    
    private final PhotoService photoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/sessions/{id}/photos", consumes = "multipart/form-data")
    public BasicPhoto uploadNewPhoto(@PathVariable("id") Long sessionId,@RequestParam MultipartFile multipartImage) throws IOException {
        var photo = photoService.addPhoto(
            multipartImage.getOriginalFilename(),
            multipartImage.getBytes(), sessionId);
        
        return new BasicPhoto(photo.getId(),photo.isFavorit(),photo.getSessionId());
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<BasicPhoto> getPhoto(@PathVariable Long id, Principal usePrincipal){
        Optional<Photo> optPhoto = photoService.getPhoto(id);
        if(optPhoto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var photo = optPhoto.get();
        
        return ResponseEntity.ok().body(new BasicPhoto(id,photo.isFavorit(),photo.getSessionId()));
    }

    @GetMapping("/photos/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable Long id){
        Optional<Photo> optPhoto = photoService.getPhoto(id);
        if(optPhoto.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(
           new ByteArrayResource(optPhoto.get().getImage()));

    }
    
}
