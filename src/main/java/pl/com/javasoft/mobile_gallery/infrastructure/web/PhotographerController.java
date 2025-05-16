package pl.com.javasoft.mobile_gallery.infrastructure.web;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.com.javasoft.mobile_gallery.application.base.DispatchableHandler;
import pl.com.javasoft.mobile_gallery.application.command.RegisterPhotographerCommand;

@RequestMapping("/api/v1/photographer")
@RestController
public class PhotographerController {

    private final DispatchableHandler dispatchableHandler;
    public PhotographerController(DispatchableHandler dispatchableHandler) {
        this.dispatchableHandler = dispatchableHandler;
    }

    public Map<String, String> registerPhotographer(RegisterPhotographerCommand registerPhotographerCommand) {
        Long photographerId = dispatchableHandler.dispatch(registerPhotographerCommand);
        if (photographerId == null) {
            throw new IllegalArgumentException("Photographer registration failed");
        }

        return Map.of("photographerId", String.valueOf(photographerId));
    }
}
