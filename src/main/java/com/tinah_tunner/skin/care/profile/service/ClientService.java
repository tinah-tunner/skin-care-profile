
package com.tinah_tunner.skin.care.profile.service;

import com.tinah_tunner.skin.care.profile.model.Client;
import com.tinah_tunner.skin.care.profile.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // ➕ Create or Save client
    public Client saveClient(Client client) {
        client.setLastUpdatedDate(LocalDate.now());
        return repository.save(client);
    }

    // 📄 Get all clients
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    // 🔍 Get one client
    public Client getClientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    // ❌ Delete client
    public void deleteClient(Long id) {
        repository.deleteById(id);
    }

    // ✏️ Update client
    public Client updateClient(Long id, Client updatedClient) {
        Client existing = getClientById(id);

        existing.setFullName(updatedClient.getFullName());
        existing.setAge(updatedClient.getAge());
        existing.setContactDetails(updatedClient.getContactDetails());

        existing.setSkinType(updatedClient.getSkinType());
        existing.setSkinTone(updatedClient.getSkinTone());
        existing.setAcneLevel(updatedClient.getAcneLevel());
        existing.setSensitivityLevel(updatedClient.getSensitivityLevel());

        existing.setAllergies(updatedClient.getAllergies());
        existing.setSkinConditions(updatedClient.getSkinConditions());

        existing.setLastUpdatedDate(LocalDate.now());

        return repository.save(existing);
    }
}