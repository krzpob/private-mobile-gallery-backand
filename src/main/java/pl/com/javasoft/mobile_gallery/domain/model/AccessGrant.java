package pl.com.javasoft.mobile_gallery.domain.model;

import java.time.LocalDateTime;


public class AccessGrant {
    Client client;
    Gallery gallery;
    LocalDateTime grantedAt;
    boolean canDownload;
}
