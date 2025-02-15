package pl.com.javasoft.mobile_gallery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.javasoft.mobile_gallery.domain.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    
}
