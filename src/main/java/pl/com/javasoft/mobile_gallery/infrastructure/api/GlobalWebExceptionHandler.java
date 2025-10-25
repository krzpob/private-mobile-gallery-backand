package pl.com.javasoft.mobile_gallery.infrastructure.api;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pl.com.javasoft.mobile_gallery.infrastructure.exceptions.GalleryApiException;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalWebExceptionHandler {
    private static final String BAD_REQUEST_ACTION = "Retry request with correct parameters";

    @ExceptionHandler(GalleryApiException.class)
    public ResponseEntity<ApiErrorResponse> handleGamingorApiException(GalleryApiException e) {
        val apiError = ApiErrorResponse.builder()
                .title(e.getTitle())
                .details(e.getDetails())
                .action(e.getAction()).build();

        logApiError(apiError);
        return ResponseEntity.status(e.getHttpStatus()).body(apiError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConversionFailedException.class)
    public ApiErrorResponse handleConversionFailedException(ConversionFailedException e) {
        val apiError = ApiErrorResponse.builder()
                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(e.getMessage())
                .action(BAD_REQUEST_ACTION).build();

        logApiError(apiError);
        return apiError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        //noinspection OptionalGetWithoutIsPresent
        val apiError = ApiErrorResponse.builder()
                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(violations.stream().findFirst().get().getMessage())
                .action(BAD_REQUEST_ACTION).build();

        logApiError(apiError);
        return apiError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        val fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField).collect(Collectors.joining());

        val apiError = ApiErrorResponse.builder()
                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(fieldErrors)
                .action(BAD_REQUEST_ACTION).build();

        logApiError(apiError);
        return apiError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ApiErrorResponse handleValidationException(ValidationException e) {
        val apiError =  ApiErrorResponse.builder()
                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(e.getMessage())
                .action(BAD_REQUEST_ACTION).build();

        logApiError(apiError);
        return apiError;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationDeniedException.class)
    public void handleAuthorizationDeniedException(AuthorizationDeniedException e) {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleGenericException(Exception e) {
        log.error("Internal Error: ", e);
        return ApiErrorResponse.builder()
                .title(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .action("Retry again or contact server administration").build();
    }


    private static void logApiError(ApiErrorResponse apiError) {
        log.warn("Api Error: {}", apiError);
    }
}
