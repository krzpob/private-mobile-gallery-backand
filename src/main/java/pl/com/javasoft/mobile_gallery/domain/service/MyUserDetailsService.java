package pl.com.javasoft.mobile_gallery.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import pl.com.javasoft.mobile_gallery.domain.model.User;
import pl.com.javasoft.mobile_gallery.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        log.info("Loaded user {} for {}", user, username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        
        return new MyUserPrincipal(user);
    }
    
}
