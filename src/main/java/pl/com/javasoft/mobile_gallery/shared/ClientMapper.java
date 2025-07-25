package pl.com.javasoft.mobile_gallery.shared;

import pl.com.javasoft.mobile_gallery.domain.model.AccessGrant;
import pl.com.javasoft.mobile_gallery.domain.model.Client;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.ClientEntity;

public class ClientMapper {

    public static Client mapToDomain(ClientEntity client) {
        return new Client(client.getId(), client.getEmail(), 
                client.getAccessGrants().stream()
                        .map(accessGrant -> AccessGrant.builder()
                                .id(accessGrant.getId())
                                .gallery(GalleryMapper.mapToDomain(accessGrant.getGallery()))
                                .canDownload(accessGrant.isCanDownload())
                                .grantedAt(accessGrant.getGrantedAt())
                                .build())
                        .toList());
    }

}
