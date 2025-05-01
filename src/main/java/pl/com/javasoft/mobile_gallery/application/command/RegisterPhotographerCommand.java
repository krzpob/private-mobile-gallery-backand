package pl.com.javasoft.mobile_gallery.application.command;

import pl.com.javasoft.mobile_gallery.application.base.Command;
import pl.com.javasoft.mobile_gallery.application.base.HandledBy;
import pl.com.javasoft.mobile_gallery.application.command.handler.RegisterPhotographerCommandHandler;

@HandledBy(handler=RegisterPhotographerCommandHandler.class)
/**
 * Command to register a new photographer.
 * <p>
 * This command contains the necessary information for registering a new photographer, including their email address,
 * name, and password.
 * </p>
 *
 * @param emailAddress The email address of the photographer.
 * @param name         The name of the photographer.
 * @param password     The password for the photographer's account.
 */
public record RegisterPhotographerCommand(String emailAddress, String name, String password) implements Command<Long> {
    
}
