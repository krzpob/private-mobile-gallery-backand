package pl.com.javasoft.mobile_gallery.infrastructure.api.token;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.com.javasoft.mobile_gallery.infrastructure.config.security.annotations.PermitAll;
import pl.com.javasoft.mobile_gallery.infrastructure.config.security.annotations.PermitOnlyAuthenticated;
import pl.com.javasoft.mobile_gallery.infrastructure.service.UserAuthenticationService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Validated
public class UserAuthenticationRestController {
    private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @PermitAll
    public JwtTokenResponse login(@Valid @RequestBody UserLoginRequest request){
        return this.userAuthenticationService.generateRefreshToken(request);
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    @PermitOnlyAuthenticated
    public JwtTokenResponse token(JwtAuthenticationToken refreshToken){
        return this.userAuthenticationService.generateAccessToken(refreshToken);
    }
}
