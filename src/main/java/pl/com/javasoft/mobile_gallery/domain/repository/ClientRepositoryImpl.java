package pl.com.javasoft.mobile_gallery.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import pl.com.javasoft.mobile_gallery.domain.model.Client;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.entity.ClientEntity;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.JpaClientEntityRepository;
import pl.com.javasoft.mobile_gallery.infrastructure.persistence.repository.UserRepository;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final JpaClientEntityRepository jpaClientEntityRepository;
    private final UserRepository userRepository;
    
    @Override
    public Optional<Client> findByEmail(String email) {
        
        return jpaClientEntityRepository.findByEmail(email)
                .map(entity -> {
                    Client client = new Client();
                    client.setEmail(entity.getEmail());
                    client.setAccessGrants(entity.getAccessGrants());
                    return client;
                });
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setEmail(client.getEmail());
        // Logic to save a client
        return client; // Placeholder return
    }

   

}
