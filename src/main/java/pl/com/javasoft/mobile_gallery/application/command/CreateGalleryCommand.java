package pl.com.javasoft.mobile_gallery.application.command;

import pl.com.javasoft.mobile_gallery.application.base.Command;
import pl.com.javasoft.mobile_gallery.application.base.HandledBy;
import pl.com.javasoft.mobile_gallery.application.command.handler.CreateGalleryCommandHandler;

@HandledBy(handler=CreateGalleryCommandHandler.class)
public record CreateGalleryCommand(Long photographerId, String name) implements Command<Long> {
    
}
