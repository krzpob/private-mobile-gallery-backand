package pl.com.javasoft.mobile_gallery.infrastructure.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.service.UserRegistrationService;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.User;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void registerUser(String email, String password, String role) {
        
        var encodedPassword = passwordEncoder.encode(password);
        var user = User.registerUser(email, encodedPassword, role);
        userRepository.saveAndFlush(user);
        
    }
    
}
