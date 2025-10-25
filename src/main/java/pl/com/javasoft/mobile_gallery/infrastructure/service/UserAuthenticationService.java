package pl.com.javasoft.mobile_gallery.infrastructure.service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.RefreshTokenJpaEntity;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.UserJpaEntity;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.RefreshTokenJpaRepository;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.RefreshTokenJpaRepository.AccessTokenGenerationProjection;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.UserJpaRepository;
import pl.com.javasoft.mobile_gallery.infrastructure.exceptions.AuthorizationException;
import pl.com.javasoft.mobile_gallery.infrastructure.exceptions.ResourceNotFoundException;
import pl.com.javasoft.mobile_gallery.infrastructure.api.token.JwtTokenResponse;
import pl.com.javasoft.mobile_gallery.infrastructure.api.token.UserLoginRequest;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.RefreshTokenJpaRepository.AccessTokenGenerationProjection.UserView;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {
    @Value("${security.jwt.expiration.seconds.refresh}")
    private Long refreshTokenExpiration;

    @Value("${security.jwt.expiration.seconds.access}")
    private Long accessTokenExpiration;

    
    private final UserJpaRepository userJpaRepository;
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    private final JwtEncoder jwtEncoder;

    @Transactional(noRollbackFor = AuthorizationException.class)
    public JwtTokenResponse generateRefreshToken(UserLoginRequest request) {
        val userEntity = this.userJpaRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", request.getUsername()));

        validateIfCorrectPassword(request.getPassword(), userEntity.getPassword());
        revokeTokensIfUserUnusable(userEntity);

        var refreshTokenEntity = toRefreshTokenEntity(userEntity, this.refreshTokenExpiration);
        refreshTokenEntity = this.refreshTokenJpaRepository.saveAndFlush(refreshTokenEntity);

        val tokenClaims = toRefreshTokenClaims(userEntity.getUsername(), refreshTokenEntity);
        val encodedToken = this.jwtEncoder.encode(JwtEncoderParameters.from(tokenClaims)).getTokenValue();

        return JwtTokenResponse.builder()
                .token(encodedToken)
                .expiresAt(tokenClaims.getExpiresAt()).build();
    }

    @Transactional(noRollbackFor = AuthorizationException.class)
    public JwtTokenResponse generateAccessToken(JwtAuthenticationToken token){
        val tokenId = extractTokenId(token.getToken().getClaims());
        val refreshTokenProjection = this.refreshTokenJpaRepository.findProjectedById(tokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh Token", tokenId.toString()));
        validateIfRevokedToken(refreshTokenProjection);

        val userProjection = refreshTokenProjection.getUser();
        validateIfCorrectUsername(token.getName(), userProjection.getUsername(), tokenId);
        revokeTokenIfUserLocked(userProjection, tokenId);

        val tokenClaims = toAccessTokenClaims(userProjection, this.accessTokenExpiration);
        val encodedToken = this.jwtEncoder.encode(JwtEncoderParameters.from(tokenClaims)).getTokenValue();

        return JwtTokenResponse.builder()
                .token(encodedToken)
                .expiresAt(tokenClaims.getExpiresAt()).build();
    }

    private static JwtClaimsSet toAccessTokenClaims(UserView userView, Long accessTokenExpiration) {
        val now = Instant.now();
        val expiresAt = now.plusSeconds(accessTokenExpiration);

        val scope = userView.getAuthorities().stream()
                .map(RefreshTokenJpaRepository.AccessTokenGenerationProjection.AuthorityView::getRole)
                .map(Enum::toString)
                .collect(Collectors.joining(" "));

        return JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(expiresAt)
                .claim("scope", scope)
                .subject(userView.getUsername())
                .build();
    }

    private static void validateIfCorrectUsername(String subject, String username, UUID tokenId) {
        if (!username.equals(subject)){
            throw new AuthorizationException("Incorrect Subject",
                 "Provided refresh token %s has incorrect subject embedded into it.".formatted(tokenId),
                 "Generate new refresh token."
         );
        }
    }

    private static void validateIfRevokedToken(AccessTokenGenerationProjection refreshTokenView) {
        if (refreshTokenView.getIsRevoked()) {
            throw new AuthorizationException("Revoked Refresh Token", 
                    "Provided refresh token %s has been already revoked.".formatted(refreshTokenView.getId()),
             "Generate new refresh token."
             );
        }
    }

    private void revokeTokenIfUserLocked(
         RefreshTokenJpaRepository.AccessTokenGenerationProjection.UserView userView,
         UUID tokenId
    ) {
        if (userView.getIsLocked()) {
            this.refreshTokenJpaRepository.updateRevokedById(tokenId, true);
            throw new AuthorizationException("Locked User",
                    "Provided refresh token %s got revoked, because user %s is locked."
                            .formatted(tokenId, userView.getUsername()), "Contact administration."
            );
        }
    }


    private static UUID extractTokenId(Map<String,?> claims) {
        if (!claims.containsKey("jti")){
            throw new AuthorizationException(
                    "Malformed Refresh Token", "Provided bearer token does not include required id.",
                    "Provide valid refresh token."
            );
        }

        return UUID.fromString(String.valueOf(claims.get("jti")));
    }

    private static JwtClaimsSet toRefreshTokenClaims(String username, RefreshTokenJpaEntity refreshTokenEntity) {
        return JwtClaimsSet.builder()
                    .id(refreshTokenEntity.getId().toString())
                    .subject(username)
                    .issuedAt(refreshTokenEntity.getCreated())
                    .expiresAt(refreshTokenEntity.getExpiresAt())
                    .build(); 
    }

    private static RefreshTokenJpaEntity toRefreshTokenEntity(UserJpaEntity userEntity, long refreshTokenExpiration) {
        val now = Instant.now();
        val expiresAt = now.plusSeconds(refreshTokenExpiration);

        return RefreshTokenJpaEntity.builder()
            .id(UUID.randomUUID())
            .user(userEntity)
            .expiresAt(expiresAt)
            .isRevoked(false)
            .created(now)
            .updated(now)
            .build();
    }

    private void revokeTokensIfUserUnusable(UserJpaEntity userEntity) {
        if(!userEntity.isVerified() || userEntity.isLocked()){
            this.refreshTokenJpaRepository.updateAllRevokedByUserId(userEntity.getId(), true);
            throw new AuthorizationException("Konto nieużywalne",
             "Użytkownik %s jest %s. Odrzucono wszystkie aktywne tokeny do oświerzenia".formatted(
                userEntity.getUsername(),!userEntity.isVerified()?"nie zweryfikowany": "zablokowany"),
              "Skontaktuj się z administartorem");
        }
    }

    private void validateIfCorrectPassword(String requestPassword, String entityPassword) {
        if (!BCrypt.checkpw(requestPassword, entityPassword)){
            throw new AuthorizationException("Niepoprawne hasło", 
                    "Podane hasło %s jest nie poprawne".formatted(requestPassword), 
                    "Podaj poprawne hasło");
        }
    }
}

