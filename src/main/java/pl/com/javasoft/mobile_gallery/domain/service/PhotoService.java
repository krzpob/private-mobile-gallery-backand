package pl.com.javasoft.mobile_gallery.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Photo;
import pl.com.javasoft.mobile_gallery.domain.model.Session;
import pl.com.javasoft.mobile_gallery.domain.repository.PhotoRepository;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final SessionService sessionService;
    private final PhotoRepository photoRepository;
    
    public Photo addPhoto(String name,byte[] image, Long sessionId){
        Session session = sessionService.getSession(sessionId);

        Photo record = new Photo(name,image,session);
        return photoRepository.save(record);
    }

    public Optional<Photo> getPhoto(Long id) {
        return photoRepository.findById(id);
    }
}
