package pl.com.javasoft.mobile_gallery.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Photo {
    private Long id;
    private String contentLocation;
    Gallery gallery;
    LocalDateTime uploadedAt; 
}
