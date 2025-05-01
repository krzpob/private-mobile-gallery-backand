package pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PhotographerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;

    @ManyToOne
    @JoinColumn
    private List<GalleryEntity> galleries = new ArrayList<>();

    public PhotographerEntity(Long id,String email,String  name){
        this.id=id;
        this.email=email;
        this.name=name;        
    }

    public void addGallery(GalleryEntity gallery){
        this.galleries.add(gallery);
    }
}
