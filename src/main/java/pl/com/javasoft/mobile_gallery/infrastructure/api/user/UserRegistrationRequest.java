package pl.com.javasoft.mobile_gallery.infrastructure.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @NotBlank(message = "Email is mandatory, but was blank.")
    @Size(max = 255, message = "Email cannot exceed 255 chars.")
    private String email;

    @NotBlank(message = "Username is mandatory, but is blank.")
    @Size(max = 30, message = "Username cannot exceed 30 chars.")
    private String username;

    @NotBlank(message = "Password is mandatory, but was blank.")
    @Size(max = 100, message = "Password cannot exceed 100 chars.")
    private String password;
}
