package pl.com.javasoft.mobile_gallery.domain.port.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSessionCommand {

    private String name;

    private LocalDateTime sessDateTime;

}
