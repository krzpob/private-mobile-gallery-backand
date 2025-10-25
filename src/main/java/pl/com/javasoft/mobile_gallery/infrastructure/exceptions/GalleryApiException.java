package pl.com.javasoft.mobile_gallery.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class GalleryApiException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String title;
    private final String details;
    private final String action;
}
