package pl.com.javasoft.mobile_gallery.infrastructure.api.token;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = false)
public class UserLoginRequest {
       
    @NotBlank(message = "Username is mandatory, but was blank.")
    @Size(max = 30, message = "Username cannot exceed 30 chars.")
    private String username;
    @NotBlank(message = "Passowrd is mandatory, but was blank")
    @Size(max = 100, message = "Password cannot exceed 100 chars")
    private String password;

}
