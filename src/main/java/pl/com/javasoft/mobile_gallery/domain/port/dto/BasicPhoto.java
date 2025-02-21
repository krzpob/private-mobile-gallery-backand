package pl.com.javasoft.mobile_gallery.domain.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BasicPhoto {
    private Long id;    
    private boolean favorit;
    private Long sessionId;
}
