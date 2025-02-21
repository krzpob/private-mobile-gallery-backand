package pl.com.javasoft.mobile_gallery.domain.port.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix="with")
public class PhotoDTO {

    private Long id;
    private String image;
    private String name;
    private boolean favorit;
    private Long sessionId;

}
