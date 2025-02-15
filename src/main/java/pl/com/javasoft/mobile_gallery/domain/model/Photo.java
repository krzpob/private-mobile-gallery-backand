package pl.com.javasoft.mobile_gallery.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id", nullable = false)
    @Id()
    Long id;

    @Column
    String image;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    Session session;
}
