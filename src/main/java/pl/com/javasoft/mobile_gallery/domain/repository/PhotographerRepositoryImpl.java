package pl.com.javasoft.mobile_gallery.domain.repository;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Photographer;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.JpaPhotographerRepository;
import pl.com.javasoft.mobile_gallery.shared.PhotographerMapper;

@Repository
@RequiredArgsConstructor
public class PhotographerRepositoryImpl implements PhotographerRepository {

    private final JpaPhotographerRepository jpaPhotographerRepository;

    @Override
    public Long save(Photographer photographer) {
        var entity = jpaPhotographerRepository.saveAndFlush(PhotographerMapper.mapToEntity(photographer));
        return entity.getId();
    }

    @Override
    public Photographer findByEmail(String email) {
        return jpaPhotographerRepository.findByEmail(email)
                .map(PhotographerMapper::mapToDomain)
                .orElse(null);
    }

    @Override
    public Photographer findById(Long id) {
        return jpaPhotographerRepository.findById(id)
                .map(PhotographerMapper::mapToDomain)
                .orElse(null);
    }
    
}
