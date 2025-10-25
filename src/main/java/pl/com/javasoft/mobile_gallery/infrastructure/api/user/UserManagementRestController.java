package pl.com.javasoft.mobile_gallery.infrastructure.api.user;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.com.javasoft.mobile_gallery.infrastructure.config.security.annotations.PermitAll;
import pl.com.javasoft.mobile_gallery.infrastructure.service.UserManagementService;
import pl.com.javasoft.mobile_gallery.infrastructure.api.user.UserRegistrationRequest;
import pl.com.javasoft.mobile_gallery.infrastructure.api.user.UserRegistrationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Validated
public class UserManagementRestController {
    private final UserManagementService userManagementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PermitAll
    public UserRegistrationResponse register(@Valid @RequestBody UserRegistrationRequest request){
        return userManagementService.registerUser(request);
    }
}
