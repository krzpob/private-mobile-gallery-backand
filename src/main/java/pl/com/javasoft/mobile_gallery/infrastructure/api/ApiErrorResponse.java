package pl.com.javasoft.mobile_gallery.infrastructure.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ApiErrorResponse {
    private String title;
    private String details;
    private String action;
}
