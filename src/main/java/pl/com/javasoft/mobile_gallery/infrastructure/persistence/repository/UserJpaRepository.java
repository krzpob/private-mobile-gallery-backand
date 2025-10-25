package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.UserJpaEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID>{
    boolean existsByUsername(String username);
    Optional<UserJpaEntity> findByUsername(String username);
}
