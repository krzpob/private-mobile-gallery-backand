package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
