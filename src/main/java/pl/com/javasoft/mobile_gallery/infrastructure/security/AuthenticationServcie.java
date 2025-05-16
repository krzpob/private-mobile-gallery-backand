package pl.com.javasoft.mobile_gallery.infrastructure.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.User;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.UserRepository;

@RequiredArgsConstructor
public class AuthenticationServcie {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User authenticate(String username, String password) {
        var user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException("Invalid password for user: " + username);
        }
        return user;
    }

    
    
}
