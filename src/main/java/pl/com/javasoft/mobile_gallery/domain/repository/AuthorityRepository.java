package pl.com.javasoft.mobile_gallery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.domain.model.Authorities;

public interface AuthorityRepository extends JpaRepository<Authorities,Long>{
     public Authorities findByEmail(String email);
}
