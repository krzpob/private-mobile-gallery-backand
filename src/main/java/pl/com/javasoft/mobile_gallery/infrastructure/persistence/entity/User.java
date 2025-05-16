package pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {

    public enum Role {
        PHOTOGRAPHER,
        CLIENT,
        ADMIN
    }

    public static final User registerUser(String email, String password, String role){
        var user = new User();
        user.email = email;
        user.password = password;
        user.role = Role.valueOf(role.toUpperCase());
        return user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role.name();
            }});
    }
    
}

