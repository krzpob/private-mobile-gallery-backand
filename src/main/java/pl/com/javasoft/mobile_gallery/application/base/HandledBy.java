package pl.com.javasoft.mobile_gallery.application.base;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandledBy {
    Class<? extends BaseHandler<? extends Dispatchable<?>, ?>> handler();
}
