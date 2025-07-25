package pl.com.javasoft.mobile_gallery.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;
import pl.com.javasoft.mobile_gallery.application.base.Command;

@Getter
public class GrantAccessForClientCommand implements Command<Void> {
    private final Long galleryId;
    private final Long clientId;
    private final boolean canDownload;

    @JsonCreator
    public GrantAccessForClientCommand(Long galleryId, Long clientId, boolean canDownload) {
        this.galleryId = galleryId;
        this.clientId = clientId;
        this.canDownload = canDownload;
    }
}
