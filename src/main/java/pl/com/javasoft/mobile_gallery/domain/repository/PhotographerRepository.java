package pl.com.javasoft.mobile_gallery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.domain.model.Photographer;

import java.util.Optional;


public interface PhotographerRepository extends JpaRepository<Photographer, Long>{
    Optional<Photographer> findOneByEmail(String email);
}
