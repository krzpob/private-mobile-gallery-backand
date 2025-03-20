package pl.com.javasoft.mobile_gallery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.domain.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}