package pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.AccessGrant;
import pl.com.javasoft.mobile_gallery.domain.model.Photo;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GalleryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private PhotographerEntity owner;

    @OneToMany
    @JoinColumn
    private List<PhotoEntity> photos;
    
    @OneToMany(mappedBy = "gallery")
    @JoinColumn
    private List<AccessGrantEntity> accessGrants;
}
