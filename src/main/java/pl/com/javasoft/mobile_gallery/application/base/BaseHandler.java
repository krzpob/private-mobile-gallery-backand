package pl.com.javasoft.mobile_gallery.application.base;

public interface BaseHandler<TDispatchable extends Dispatchable<TResult>, TResult> {
   TResult handle(TDispatchable dispatchable); 
}
