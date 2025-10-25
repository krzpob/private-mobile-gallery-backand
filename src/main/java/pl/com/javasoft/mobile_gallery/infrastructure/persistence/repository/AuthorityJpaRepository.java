package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.com.javasoft.mobile_gallery.infrastructure.config.security.Role;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.AuthorityJpaEntity;

@Repository
public interface AuthorityJpaRepository extends JpaRepository<AuthorityJpaEntity,Short> {

    Set<AuthorityJpaEntity> findByRoleIn(Set<Role> roles);


}
