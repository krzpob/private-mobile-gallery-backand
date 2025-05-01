package pl.com.javasoft.mobile_gallery.application.command.handler;

import pl.com.javasoft.mobile_gallery.application.base.CommandHandler;
import pl.com.javasoft.mobile_gallery.application.command.RegisterPhotographerCommand;

public class RegisterPhotographerCommandHandler implements CommandHandler<RegisterPhotographerCommand, Long> {
    @Override
    public Long handle(RegisterPhotographerCommand command) {
        
        return 0L;
    }
    
}
