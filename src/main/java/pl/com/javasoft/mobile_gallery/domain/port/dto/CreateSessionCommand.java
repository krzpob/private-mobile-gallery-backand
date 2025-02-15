package pl.com.javasoft.mobile_gallery.domain.port.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateSessionCommand {

    private String name;

    private LocalDateTime sessDateTime;

}
