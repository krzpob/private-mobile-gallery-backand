package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.domain.model.Client;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.ClientEntity;

public interface JpaClientEntityRepository extends JpaRepository<ClientEntity, Long> {

    Optional<Client> findByEmail(String email);

    
}
