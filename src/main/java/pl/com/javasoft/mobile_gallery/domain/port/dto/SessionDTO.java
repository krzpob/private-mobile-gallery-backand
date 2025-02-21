package pl.com.javasoft.mobile_gallery.domain.port.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;


@Builder(setterPrefix = "with")
@Getter
public class SessionDTO {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Builder.Default
    private List<BasicPhoto> photos = new ArrayList<>();
    private LocalDateTime sessionDate;
    private String name;
    private Long id;
}
