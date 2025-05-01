package pl.com.javasoft.mobile_gallery.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessGrant {
    private Long id;
    private Client client;
    private Gallery gallery;
    private LocalDateTime grantedAt;
    private boolean canDownload;


}
