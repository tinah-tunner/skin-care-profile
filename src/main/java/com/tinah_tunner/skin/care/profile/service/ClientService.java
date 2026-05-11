package com.tinah_tunner.skin.care.profile.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tinah_tunner.skin.care.profile.model.*;
import com.tinah_tunner.skin.care.profile.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client saveClient(Client client) {
        client.setLastUpdatedDate(LocalDate.now());
        return repository.save(client);
    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Client getClientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    public void deleteClient(Long id) {
        repository.delete(getClientById(id));
    }

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

    public Client partialUpdateClient(Long id, Map<String, Object> updates) {

        Client existing = getClientById(id);

        updates.forEach((key, value) -> {

            switch (key) {

                case "fullName" -> existing.setFullName(value.toString());

                case "age" -> existing.setAge(
                        Integer.parseInt(value.toString())
                );

                case "contactDetails" -> existing.setContactDetails(value.toString());

                case "skinType" -> existing.setSkinType(
                        SkinType.valueOf(value.toString().toUpperCase())
                );

                case "skinTone" -> existing.setSkinTone(value.toString());

                case "acneLevel" -> existing.setAcneLevel(
                        AcneLevel.valueOf(value.toString().toUpperCase())
                );

                case "sensitivityLevel" -> existing.setSensitivityLevel(
                        SensitivityLevel.valueOf(value.toString().toUpperCase())
                );

                case "allergies" -> existing.setAllergies(value.toString());

                case "skinConditions" -> existing.setSkinConditions(value.toString());

                default -> throw new IllegalArgumentException("Unknown field: " + key);
            }
        });

        existing.setLastUpdatedDate(LocalDate.now());

        return repository.save(existing);
    }
}