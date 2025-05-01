package pl.com.javasoft.mobile_gallery.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Gallery;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.GalleryEntity;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.JpaGalleryRepository;
import pl.com.javasoft.mobile_gallery.shared.GalleryMapper;

@Repository
@RequiredArgsConstructor
public class GalleryRepositoryImpl implements GalleryRepository{
    private final JpaGalleryRepository jpaGalleryRepository;

    @Override
    public Optional<Gallery> findById(Long id) {
        return jpaGalleryRepository.findById(id)
                .map(GalleryMapper::mapToDomain);
    }

    @Override
    public List<Gallery> findByPhotographerId(Long photographerId) {
        return jpaGalleryRepository.findByPhotographerId(photographerId).stream()
                .map(GalleryMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Gallery save(Gallery gallery) {
        GalleryEntity entity = GalleryMapper.mapToEntity(gallery);
        GalleryEntity saved = jpaGalleryRepository.save(entity);
        return GalleryMapper.mapToDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaGalleryRepository.deleteById(id);
    }

  
}
