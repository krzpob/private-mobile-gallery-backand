package pl.com.javasoft.mobile_gallery.infrastructure.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import pl.com.javasoft.mobile_gallery.infrastructure.config.security.Role;
import pl.com.javasoft.mobile_gallery.infrastructure.exceptions.ResourceAlreadyExistException;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.AuthorityJpaEntity;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.UserJpaEntity;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.AuthorityJpaRepository;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.UserJpaRepository;
import pl.com.javasoft.mobile_gallery.infrastructure.api.user.UserRegistrationRequest;
import pl.com.javasoft.mobile_gallery.infrastructure.api.user.UserRegistrationResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final AuthorityJpaRepository authorityJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public UserRegistrationResponse registerUser(UserRegistrationRequest request){
        if (userJpaRepository.existsByUsername(request.getUsername())) {
            throw new ResourceAlreadyExistException("User", request.getUsername());
        }

        val authorities = fetchAuthorities(Role.CLIENT);
        var user = instantiateUserEntity(request, authorities);

        user = this.userJpaRepository.saveAndFlush(user);

        return toUserRegistrationResponse(user);
    }

    @Transactional
    public UserRegistrationResponse registerPhotograph(UserRegistrationRequest request){
        if (userJpaRepository.existsByUsername(request.getUsername())) {
            throw new ResourceAlreadyExistException("User", request.getUsername());
        }

        val authorities = fetchAuthorities(Role.PHOTOGRAPH);
        var user = instantiateUserEntity(request, authorities);

        user = this.userJpaRepository.saveAndFlush(user);

        return toUserRegistrationResponse(user);
    }

    private UserRegistrationResponse toUserRegistrationResponse(UserJpaEntity user) {
        return UserRegistrationResponse.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .created(user.getCreated())
                    .updated(user.getUpdated())
                    .build();
    }
    

    private Set<AuthorityJpaEntity> fetchAuthorities(Role... roles) {
        val authorities = authorityJpaRepository.findByRoleIn(Set.of(roles));
        
        if (authorities.isEmpty() || authorities.size()!= roles.length) {
            throw new RuntimeException("App tried to assign non-existing role to user");
        }

        return authorities;
    }

    private UserJpaEntity instantiateUserEntity(UserRegistrationRequest request, Set<AuthorityJpaEntity> authorities) {
       return UserJpaEntity.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .isLocked(false)
                .isVerified(true)
                .authorities(authorities)
                .build();
    }

    





}
