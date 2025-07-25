package pl.com.javasoft.mobile_gallery.application.command.handler;

import org.springframework.stereotype.Service;

import pl.com.javasoft.mobile_gallery.application.base.CommandHandler;
import pl.com.javasoft.mobile_gallery.application.command.GrantAccessForClientCommand;
import pl.com.javasoft.mobile_gallery.domain.service.GrantAccessService;

@Service
public class GrantAccessForClientCommandHandler implements CommandHandler<GrantAccessForClientCommand, Void> {

    private final GrantAccessService grantAccessService;

    public GrantAccessForClientCommandHandler(GrantAccessService grantAccessService) {
        this.grantAccessService = grantAccessService;
    }

    @Override
    public Void handle(GrantAccessForClientCommand command) {
        // Validate command parameters
        if (command.getGalleryId() == null || command.getClientId() == null) {
            throw new IllegalArgumentException("Gallery ID and Client ID must not be null");
        }

        // Call the service to grant access
        grantAccessService.grantAccess(command.getGalleryId(), command.getClientId(), command.isCanDownload());

        return null; // No return value needed for this command
    }

}
