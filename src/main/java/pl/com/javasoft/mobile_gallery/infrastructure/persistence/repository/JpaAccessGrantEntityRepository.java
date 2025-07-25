package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.AccessGrantEntity;

public interface JpaAccessGrantEntityRepository extends JpaRepository<AccessGrantEntity, Long> {

    /**
     * Finds all access grants for a given client ID.
     *
     * @param clientId the ID of the client
     * @return a list of AccessGrantEntity objects associated with the client
     */
    List<AccessGrantEntity> findByClientId(Long clientId);

    /**
     * Finds all access grants for a given gallery ID.
     *
     * @param galleryId the ID of the gallery
     * @return a list of AccessGrantEntity objects associated with the gallery
     */
    List<AccessGrantEntity> findByGalleryId(Long galleryId);

}
