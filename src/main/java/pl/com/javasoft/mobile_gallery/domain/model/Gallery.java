package pl.com.javasoft.mobile_gallery.domain.model;

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
}
