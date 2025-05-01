package pl.com.javasoft.mobile_gallery.domain.repository;

import java.util.List;
import java.util.Optional;

import pl.com.javasoft.mobile_gallery.domain.model.Gallery;

public interface GalleryRepository {
    Optional<Gallery> findById(Long id);
    List<Gallery> findByPhotographerId(Long photographerId);
    Gallery save(Gallery gallery);
    void deleteById(Long id);
}
