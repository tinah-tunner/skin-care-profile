package com.tinah_tunner.skin.care.profile.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tinah_tunner.skin.care.profile.model.AcneLevel;
import com.tinah_tunner.skin.care.profile.model.Client;
import com.tinah_tunner.skin.care.profile.model.SensitivityLevel;
import com.tinah_tunner.skin.care.profile.model.SkinType;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    @Test
    void testSaveAndFindById() {
        Client client = new Client();
        client.setFullName("John Doe");
        client.setAge(30);
        client.setContactDetails("john@example.com");
        client.setSkinType(SkinType.OILY);
        client.setAcneLevel(AcneLevel.MODERATE);
        client.setSensitivityLevel(SensitivityLevel.MILD);

        Client saved = repository.save(client);
        assertNotNull(saved.getId());

        Optional<Client> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getFullName());
    }

    @Test
    void testFindAll() {
        Client client1 = new Client();
        client1.setFullName("John Doe");

        Client client2 = new Client();
        client2.setFullName("Jane Doe");

        repository.save(client1);
        repository.save(client2);

        List<Client> clients = repository.findAll();
        assertTrue(clients.size() >= 2);
    }

    @Test
    void testDeleteById() {
        Client client = new Client();
        client.setFullName("John Doe");

        Client saved = repository.save(client);
        Long id = saved.getId();

        repository.deleteById(id);

        Optional<Client> found = repository.findById(id);
        assertFalse(found.isPresent());
    }
}