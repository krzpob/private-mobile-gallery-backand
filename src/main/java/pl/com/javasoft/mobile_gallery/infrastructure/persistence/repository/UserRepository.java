package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
