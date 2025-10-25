package pl.com.javasoft.mobile_gallery.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistException extends GalleryApiException{

    public ResourceAlreadyExistException(String resourceName, String resourceId){
        super(HttpStatus.BAD_REQUEST, 
        "%s already exists.".formatted(resourceName),
        "%s with identifier %s already exists.".formatted(resourceName, resourceId)
        ,"Try %s with other identifier.".formatted(resourceName));
    }

}
