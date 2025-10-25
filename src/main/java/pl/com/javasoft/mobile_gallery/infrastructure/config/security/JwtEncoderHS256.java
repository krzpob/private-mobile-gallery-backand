package com.ml.gamingor.configuration.security;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class JwtEncoderHS256 implements JwtEncoder{
    private final JwtEncoder jwtEncoder;

    @Override
    public Jwt encode(JwtEncoderParameters parameters) throws JwtEncodingException {
        val algorithmEnrichedParameters = JwtEncoderParameters.from(
            JwsHeader.with(MacAlgorithm.HS256).build(),
            parameters.getClaims()
        );

        return this.jwtEncoder.encode(algorithmEnrichedParameters);
    }

    
}
