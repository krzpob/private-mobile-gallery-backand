package pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.com.javasoft.mobile_gallery.infrastructure.config.security.Role;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.RefreshTokenJpaEntity;

@Repository
public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenJpaEntity,UUID> {
    
    @Modifying
    @Query("UPDATE RefreshTokenJpaEntity token SET token.isRevoked = :revoked WHERE token.id = :id")
    void updateRevokedById(@Param("id")UUID id, @Param("revoked")boolean revoked );

    
    @Modifying
    @Query("UPDATE RefreshTokenJpaEntity token SET token.isRevoked = :revoked, token.updated = INSTANT WHERE token.user.id = :userId")
    void updateAllRevokedByUserId(@Param("userId") UUID userId, @Param("revoked") boolean revoked);

    public interface AccessTokenGenerationProjection {
    
        UUID getId();
        boolean getIsRevoked();
        UserView getUser();

        public interface UserView {
            String getUsername();
            Boolean getIsLocked();
            Set<AuthorityView> getAuthorities();
            
        }

        interface AuthorityView{
            Role getRole();
        }
        
    }

    Optional<AccessTokenGenerationProjection> findProjectedById(UUID id);
}
