package pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "photos")
@NoArgsConstructor
@Getter
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contentLocation;
    
    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private GalleryEntity gallery;
    
    @CreatedDate
    LocalDateTime uploadedAt; 

    public PhotoEntity(Long id,String contentLocation, GalleryEntity gallery) {
        this.id = id;
        this.contentLocation = contentLocation;
        this.gallery = gallery;
    }
}
