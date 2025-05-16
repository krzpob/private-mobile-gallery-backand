package pl.com.javasoft.mobile_gallery.infrastructure.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecuritySettings {
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request ->             
                request
                .requestMatchers("/authentication").permitAll()
                .requestMatchers("/api/v1/photographer/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/gallery/**").hasRole("PHOTOGRAPHER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/gallery/**").hasRole("PHOTOGRAPHER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/gallery/**").hasRole("PHOTOGRAPHER")
                .requestMatchers(HttpMethod.GET, "/api/v1/gallery/**").hasAnyRole("PHOTOGRAPHER", "CLIENT")
                .anyRequest()
                .authenticated())                
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf->csrf.disable());
        return http.build();
    }


    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        var bcrypt = new BCryptPasswordEncoder();
        
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", bcrypt);
        encoders.put("scrypt", new SCryptPasswordEncoder(2, 1, 1, 1, 10));

        DelegatingPasswordEncoder passworEncoder = new DelegatingPasswordEncoder(
        "bcrypt", encoders);
        passworEncoder.setDefaultPasswordEncoderForMatches(bcrypt);

        return passworEncoder;
    }
}
