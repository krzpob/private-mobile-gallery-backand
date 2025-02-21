package pl.com.javasoft.mobile_gallery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.domain.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    
}
