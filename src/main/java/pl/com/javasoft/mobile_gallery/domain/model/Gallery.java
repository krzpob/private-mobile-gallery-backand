package pl.com.javasoft.mobile_gallery.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Gallery {
    private Long id;
    private String name;
    private Photographer owner;
    List<Photo> photos;
    List<AccessGrant> accessGrants;
    public AccessGrant grantAccess(Client client, boolean canDownload) {
        AccessGrant accessGrant = AccessGrant.builder()
                .gallery(this)
                .grantedAt(LocalDateTime.now())
                .canDownload(canDownload)
                .build();
        accessGrants.add(accessGrant);
        client.getAccessGrants().add(accessGrant);
        return accessGrant;
    }
    
}
