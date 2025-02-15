package pl.com.javasoft.mobile_gallery.domain.port.dto;

import lombok.Data;

@Data
public class CustomerCreateCommand {
    private String email;
    private String password;
}
