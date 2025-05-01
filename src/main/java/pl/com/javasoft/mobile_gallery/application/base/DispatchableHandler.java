package pl.com.javasoft.mobile_gallery.application.base;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DispatchableHandler {

    private final ApplicationContext applicationContext;

    public DispatchableHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public <TResult> TResult dispatch(final Dispatchable<TResult> dispatchable) {
        HandledBy handledByAnnotation = dispatchable.getClass().getAnnotation(HandledBy.class);
        if (handledByAnnotation == null) {
          throw new IllegalStateException(String.format("No @HandledBy annotation provided for dispatchable %s", dispatchable.getClass().getSimpleName()));
        }
        Class<? extends BaseHandler> handlerType = handledByAnnotation.handler();
        Map<String, ? extends BaseHandler> handlers = applicationContext.getBeansOfType(handlerType);
        if (handlers.isEmpty()) {
          throw new IllegalStateException(String.format("Dispatchable %s has no handler", dispatchable.getClass().getSimpleName()));
        }
        if (handlers.size() > 1) {
          throw new IllegalStateException(String.format("Dispatchable %s has more than one handler", dispatchable.getClass().getSimpleName()));
        }
        BaseHandler<Dispatchable<TResult>, TResult> handler = handlers.values().iterator().next();
        return handler.handle(dispatchable);
      }
}