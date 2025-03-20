package pl.com.javasoft.mobile_gallery.domain.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(name="ix_auth_email",columnNames = {"email","authority"})})
@Entity
@ToString
public class Authorities implements GrantedAuthority {
    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Setter
    private String email;
    @Setter
    @Enumerated(EnumType.STRING)
    private AuthorityType authority;

    public String getAuthority(){
        return authority.name();
    }

    public AuthorityType getAuthorityAsEnum(){
        return authority;
    }

    public static enum AuthorityType{
        USER, CUSTOMER, PHOTOGRAPHER, ADMIN
    }
}
