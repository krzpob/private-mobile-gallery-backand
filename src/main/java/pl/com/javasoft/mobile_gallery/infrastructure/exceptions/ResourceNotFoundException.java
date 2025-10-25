package pl.com.javasoft.mobile_gallery.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends GalleryApiException{
    public ResourceNotFoundException(String resourceName, String resourceId) {
        super(HttpStatus.NOT_FOUND,
                "%s not found.".formatted(resourceName),
                "Couldn't find %s with identifier %s.".formatted(resourceName, resourceId),
                "Provide correct identifier for %s.".formatted(resourceName)
        );
    }
}
