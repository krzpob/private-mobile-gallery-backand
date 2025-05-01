package pl.com.javasoft.mobile_gallery.domain.model;

import java.util.List;

public class Gallery {
    private String name;
    private Photographer owner;
    List<Photo> photos;
    List<AccessGrant> accessGrants;
}
