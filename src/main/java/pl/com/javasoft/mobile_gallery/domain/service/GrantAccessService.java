package pl.com.javasoft.mobile_gallery.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.com.javasoft.mobile_gallery.domain.model.Client;
import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Gallery;
import pl.com.javasoft.mobile_gallery.domain.repository.ClientRepository;
import pl.com.javasoft.mobile_gallery.domain.repository.GalleryRepository;

@Service
@RequiredArgsConstructor
public class GrantAccessService {

    private final GalleryRepository galleryRepository;
    private final ClientRepository clientRepository;

    public void grantAccess(Long galleryId, Long clientId, boolean canDownload) {
        Optional<Gallery> galleryOpt = galleryRepository.findById(galleryId);
        Optional<Client> clientOpt = clientRepository.findById(clientId);

        if(galleryOpt.isEmpty() || clientOpt.isEmpty()) {
            throw new IllegalArgumentException("Gallery or Client not found");
        }

        Gallery gallery = galleryOpt.get();
        Client client = clientOpt.get();
        gallery.grantAccess(client, canDownload);

        galleryRepository.save(gallery);
        clientRepository.save(client);
    }

}
