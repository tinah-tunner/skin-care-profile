package com.tinah_tunner.skin.care.profile.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tinah_tunner.skin.care.profile.model.AcneLevel;
import com.tinah_tunner.skin.care.profile.model.Client;
import com.tinah_tunner.skin.care.profile.model.SensitivityLevel;
import com.tinah_tunner.skin.care.profile.model.SkinType;
import com.tinah_tunner.skin.care.profile.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // CREATE CLIENT
    public Client saveClient(Client client) {
        client.setLastUpdatedDate(LocalDate.now());
        return repository.save(client);
    }

    // GET ALL CLIENTS
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    // GET CLIENT BY ID
    public Client getClientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    // DELETE CLIENT
    public void deleteClient(Long id) {
        Client existing = getClientById(id);
        repository.delete(existing);
    }

    // FULL UPDATE
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

    // PARTIAL UPDATE (PATCH)
    public Client partialUpdateClient(Long id, Map<String, Object> updates) {

        Client existing = getClientById(id);

        updates.forEach((key, value) -> {

            switch (key) {

                case "fullName":
                    existing.setFullName((String) value);
                    break;

                case "age":
                    existing.setAge((Integer) value);
                    break;

                case "contactDetails":
                    existing.setContactDetails((String) value);
                    break;

                case "skinType":
                    existing.setSkinType(
                            SkinType.valueOf(value.toString().toUpperCase()));
                    break;

                case "skinTone":
                    existing.setSkinTone((String) value);
                    break;

                case "acneLevel":
                    existing.setAcneLevel(
                            AcneLevel.valueOf(value.toString().toUpperCase()));
                    break;

                case "sensitivityLevel":
                    existing.setSensitivityLevel(
                            SensitivityLevel.valueOf(value.toString().toUpperCase()));
                    break;

                case "allergies":
                    existing.setAllergies((String) value);
                    break;

                case "skinConditions":
                    existing.setSkinConditions((String) value);
                    break;

                default:
                    throw new IllegalArgumentException("Unknown field: " + key);
            }
        });

        existing.setLastUpdatedDate(LocalDate.now());

        return repository.save(existing);
    }
}