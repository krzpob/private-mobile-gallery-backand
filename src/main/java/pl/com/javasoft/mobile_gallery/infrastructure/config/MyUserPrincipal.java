package pl.com.javasoft.mobile_gallery.infrastructure.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.User;


public class MyUserPrincipal implements UserDetails{
    private User user;

    public MyUserPrincipal(User user){
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    
}
