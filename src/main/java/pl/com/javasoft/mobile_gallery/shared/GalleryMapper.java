package pl.com.javasoft.mobile_gallery.shared;


import pl.com.javasoft.mobile_gallery.domain.model.Gallery;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.GalleryEntity;


public class GalleryMapper {
    public static Gallery mapToDomain(GalleryEntity entity) {
        return Gallery.builder()
            .id(entity.getId())
            .name(entity.getName())
            .owner(PhotographerMapper.mapToDomain(entity.getOwner()))
            .photos(null) // TODO: map photos
            .build();
    }

    public static GalleryEntity mapToEntity(Gallery gallery) {
        GalleryEntity entity = new GalleryEntity(
            gallery.getId(),
            gallery.getName(),
            PhotographerMapper.mapToEntity(gallery.getOwner()),
            null,null
            );
        
        return entity;
    }
}
