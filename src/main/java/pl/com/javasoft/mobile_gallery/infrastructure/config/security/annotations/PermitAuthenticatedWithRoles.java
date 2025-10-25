package pl.com.javasoft.mobile_gallery.infrastructure.config.security.annotations;

import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole({roles})")
public @interface PermitAuthenticatedWithRoles {
    String[] roles();
}
