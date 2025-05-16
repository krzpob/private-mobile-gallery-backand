package pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "photographers")
@NoArgsConstructor
public class PhotographerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;

    @OneToMany(mappedBy = "owner")
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
