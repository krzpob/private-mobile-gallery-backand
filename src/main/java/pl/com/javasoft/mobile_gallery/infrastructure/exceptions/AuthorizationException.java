package pl.com.javasoft.mobile_gallery.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends GalleryApiException {
    public AuthorizationException(String title, String details, String action) {
        super(HttpStatus.FORBIDDEN, title, details, action);
    }
}

