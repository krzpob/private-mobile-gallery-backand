package pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contentLocation;
    GalleryEntity gallery;
    
    @CreatedDate
    LocalDateTime uploadedAt; 

    public PhotoEntity(Long id,String contentLocation, GalleryEntity gallery) {
        this.id = id;
        this.contentLocation = contentLocation;
        this.gallery = gallery;
    }
}
