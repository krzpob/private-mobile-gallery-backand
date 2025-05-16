package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.PhotographerEntity;

public interface JpaPhotographerRepository extends JpaRepository<PhotographerEntity, Long> {
    Optional<PhotographerEntity> findByEmail(String email);

    Optional<PhotographerEntity> findById(Long id);
    
}
