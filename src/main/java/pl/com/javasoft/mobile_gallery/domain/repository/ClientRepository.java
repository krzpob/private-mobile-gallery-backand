package pl.com.javasoft.mobile_gallery.domain.repository;

import java.util.Optional;

import pl.com.javasoft.mobile_gallery.domain.model.Client;

public interface ClientRepository {
    Optional<Client> findByEmail(String email);
    Client save(Client client);

}
