package pl.com.javasoft.mobile_gallery.domain.model;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Client {
    private Long id;
    private String email;
    private List<AccessGrant> accessGrants;

    public Client(String email) {
        this(null, email, Collections.emptyList());
    }

    public Client(Long id, String email) {
        this(id, email, Collections.emptyList());
    }
}
