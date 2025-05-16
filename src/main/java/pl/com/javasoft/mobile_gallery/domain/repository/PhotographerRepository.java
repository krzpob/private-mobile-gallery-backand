package pl.com.javasoft.mobile_gallery.domain.repository;

import org.springframework.stereotype.Repository;

import pl.com.javasoft.mobile_gallery.domain.model.Photographer;

@Repository
public interface PhotographerRepository {
    Long save(Photographer photographer);

    Photographer findByEmail(String email);

    Photographer findById(Long id);
}
