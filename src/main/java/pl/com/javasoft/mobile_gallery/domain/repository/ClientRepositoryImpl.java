package pl.com.javasoft.mobile_gallery.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.AccessGrant;
import pl.com.javasoft.mobile_gallery.domain.model.Client;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.ClientEntity;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.JpaClientEntityRepository;
import pl.com.javasoft.mobile_gallery.shared.ClientMapper;
import pl.com.javasoft.mobile_gallery.shared.GalleryMapper;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.AccessGrantEntity;


@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final JpaClientEntityRepository jpaClientEntityRepository;
    
    @Override
    public Optional<Client> findByEmail(String email) {
        
        return jpaClientEntityRepository.findByEmail(email)
                .map(entity -> new Client(entity.getId(), entity.getEmail()));
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = new ClientEntity(
            client.getId(),
            client.getEmail(),
            client.getAccessGrants().stream()
                .map(accessGrant -> new AccessGrantEntity(
                        accessGrant.getId(),
                        GalleryMapper.mapToEntity(accessGrant.getGallery()),
                        accessGrant.isCanDownload()
                ))
                .toList()
        );
        jpaClientEntityRepository.saveAndFlush(entity);
        // Logic to save a client
        return client; // Placeholder return
    }

    @Override
    public Optional<Client> findById(Long id) {
        return jpaClientEntityRepository.findById(id)
                .map(entity -> new Client(entity.getId(), entity.getEmail(), 
                entity.getAccessGrants().stream()
                        .map(accessGrant -> AccessGrant.builder()
                                .id(accessGrant.getId())
                                .gallery(GalleryMapper.mapToDomain(accessGrant.getGallery()))
                                .canDownload(accessGrant.isCanDownload())
                                .grantedAt(accessGrant.getGrantedAt())
                                .build())
                        .toList()));
    }

   

}
