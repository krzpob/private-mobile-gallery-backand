package pl.com.javasoft.mobile_gallery.shared;

import pl.com.javasoft.mobile_gallery.domain.model.Photo;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.PhotoEntity;

public class PhotoMapper {
    
    public static PhotoEntity mapToEntity(Photo photo) {
        return new PhotoEntity(
            photo.getId(), 
            photo.getContentLocation(), 
            GalleryMapper.mapToEntity(photo.getGallery())
        );
    }
    
    public static Photo mapToDomain(PhotoEntity photoEntity) {
        return Photo.builder()
            .id(photoEntity.getId())
            .contentLocation(photoEntity.getContentLocation())
            .gallery(GalleryMapper.mapToDomain(photoEntity.getGallery()))
            .uploadedAt(photoEntity.getUploadedAt())
            .build();
    }
        
}
