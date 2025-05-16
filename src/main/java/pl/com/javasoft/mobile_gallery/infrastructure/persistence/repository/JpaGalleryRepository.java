package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.GalleryEntity;

public interface JpaGalleryRepository extends JpaRepository<GalleryEntity, Long> {
    List<GalleryEntity> findByOwner_Id(Long photographerId);
}
