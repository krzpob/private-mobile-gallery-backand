package pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "access_grants")
public class AccessGrantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private ClientEntity client;
    @ManyToOne
    @JoinColumn
    private GalleryEntity gallery;
    private LocalDateTime grantedAt;
    private boolean canDownload;

    public AccessGrantEntity(Long id, GalleryEntity galleryEntity, boolean canDownload) {
        this.id = id;
        this.gallery = galleryEntity; // Assuming GalleryEntity has a constructor that accepts an ID
        this.canDownload = canDownload;
        this.grantedAt = LocalDateTime.now(); // Default to now, can be overridden if needed
    }
}