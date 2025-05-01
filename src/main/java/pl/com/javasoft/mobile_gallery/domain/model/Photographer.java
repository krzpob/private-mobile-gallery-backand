package pl.com.javasoft.mobile_gallery.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Photographer {
    private Long id;
    private String email;
    private String name;
}
