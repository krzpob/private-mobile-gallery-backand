package pl.com.javasoft.mobile_gallery.infrastructure.config.security;

import static org.springframework.core.annotation.AnnotatedElementUtils.hasAnnotation;

import java.lang.reflect.AnnotatedElement;
import java.util.function.Supplier;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.authorization.method.AuthorizationInterceptorsOrder;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AnnotationTemplateExpressionDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;


import lombok.val;

import pl.com.javasoft.mobile_gallery.MobileGalleryApplication;

@Configuration
@EnableMethodSecurity
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class MethodSecurityConfig {

    private static final String APP_METHOD_POINTCUT_PATTERN = "%s.*".formatted(
            MobileGalleryApplication.class.getPackage().getName()
    );

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    Advisor preSecurityAnnotationsProcessing() {
        return DenyAllByDefaultMethodSecurityAuthorizationManager.toControllerConfiguredInterceptor(
                APP_METHOD_POINTCUT_PATTERN, AuthorizationInterceptorsOrder.FIRST.getOrder()
        );
    }

    @Bean
    static AnnotationTemplateExpressionDefaults templateExpressionDefaults() {
        return new AnnotationTemplateExpressionDefaults();
    }

    private static class DenyAllByDefaultMethodSecurityAuthorizationManager implements AuthorizationManager<MethodInvocation>{

            public static AuthorizationManagerBeforeMethodInterceptor toControllerConfiguredInterceptor(
                String methodPointcutPattern, int order
        ) {
            val appControllerPattern = new JdkRegexpMethodPointcut();
            appControllerPattern.setPattern(methodPointcutPattern);
            appControllerPattern.setClassFilter(clazz ->
                    hasAnnotation(clazz, Controller.class) || hasAnnotation(clazz, RestController.class)
            );

            val interceptor = new AuthorizationManagerBeforeMethodInterceptor(
                    appControllerPattern, new DenyAllByDefaultMethodSecurityAuthorizationManager()
            );
            interceptor.setOrder(order);

            return interceptor;
        }

        @Override
        public AuthorizationResult authorize(Supplier<Authentication> auth, MethodInvocation invocation) {
            val invokedMethod = invocation.getMethod();

            if (doesAnySecurityAnnotationExist(invokedMethod) ||
                    doesAnySecurityAnnotationExist(invokedMethod.getDeclaringClass())) {
                return null;
            }

            return new AuthorizationDecision(false);
        }

        private boolean doesAnySecurityAnnotationExist(AnnotatedElement annotatedElement) {
            val annotations = MergedAnnotations.from(
                    annotatedElement, MergedAnnotations.SearchStrategy.DIRECT
            );

            return annotations.get(PreAuthorize.class).isPresent() ||
                    annotations.get(PreFilter.class).isPresent() ||
                    annotations.get(PostAuthorize.class).isPresent() ||
                    annotations.get(PostFilter.class).isPresent();
        }

        @Deprecated
        @Override
        public AuthorizationDecision check(Supplier<Authentication> auth, MethodInvocation invocation) {
            return null;
        }
    
        
    }

}
