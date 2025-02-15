package pl.com.javasoft.mobile_gallery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.domain.model.Customer;
import pl.com.javasoft.mobile_gallery.domain.model.Session;
import java.util.List;


public interface SessionRepository extends JpaRepository<Session,Long> {
    
    List<Session> findByCustomer(Customer customer);
}
