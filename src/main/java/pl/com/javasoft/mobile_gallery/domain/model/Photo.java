package pl.com.javasoft.mobile_gallery.domain.model;


import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "photos")
@NoArgsConstructor
public class Photo {
    
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id", nullable = false)
    @Id()
    Long id;

    @Getter
    String name;

    @Lob
    @Column(length = 10485760)
    @Getter
    byte[] image;

    @Getter
    boolean favorit;


    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    Session session;

    public Long getSessionId(){
        return session.getId();
    }

    public Photo(String name, byte[] image, Session session) {
        this.image=Arrays.copyOf(image, image.length);
        this.name=name;
        this.customer=session.getCustomer();
        this.session=session;
    }
    
}
