package pl.com.javasoft.mobile_gallery.shared;

import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.PhotographerEntity;

public class PhotographerMapper {
    public static Photographer mapToDomain(PhotographerEntity photographerEntity){
        return Photographer.builder()
        .email(photographerEntity.getEmail())
        .id(photographerEntity.getId())
        .name(photographerEntity.getName())
        .build();
    }

    public static PhotographerEntity mapToEntity(Photographer owner) {
        return new PhotographerEntity(
            owner.getId(),
            owner.getEmail(),
            owner.getName()
        );
    }
    
}