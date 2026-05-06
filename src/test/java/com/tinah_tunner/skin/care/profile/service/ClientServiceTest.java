package com.tinah_tunner.skin.care.profile.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.tinah_tunner.skin.care.profile.model.AcneLevel;
import com.tinah_tunner.skin.care.profile.model.Client;
import com.tinah_tunner.skin.care.profile.model.SensitivityLevel;
import com.tinah_tunner.skin.care.profile.model.SkinType;
import com.tinah_tunner.skin.care.profile.repository.ClientRepository;

class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClient() {
        Client client = new Client();
        client.setFullName("John Doe");
        client.setAge(30);
        client.setContactDetails("john@example.com");
        client.setSkinType(SkinType.OILY);
        client.setAcneLevel(AcneLevel.MODERATE);
        client.setSensitivityLevel(SensitivityLevel.MILD);

        when(repository.save(any(Client.class))).thenReturn(client);

        Client savedClient = service.saveClient(client);

        assertNotNull(savedClient);
        assertEquals("John Doe", savedClient.getFullName());
        verify(repository, times(1)).save(client);
    }

    @Test
    void testGetAllClients() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setFullName("John Doe");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setFullName("Jane Doe");

        List<Client> clients = Arrays.asList(client1, client2);

        when(repository.findAll()).thenReturn(clients);

        List<Client> result = service.getAllClients();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetClientById() {
        Client client = new Client();
        client.setId(1L);
        client.setFullName("John Doe");

        when(repository.findById(1L)).thenReturn(Optional.of(client));

        Client result = service.getClientById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getFullName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetClientByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.getClientById(1L);
        });

        assertEquals("Client not found with id: 1", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testDeleteClient() {
        service.deleteClient(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateClient() {
        Client existing = new Client();
        existing.setId(1L);
        existing.setFullName("John Doe");
        existing.setAge(30);
        existing.setContactDetails("john@example.com");
        existing.setSkinType(SkinType.OILY);
        existing.setAcneLevel(AcneLevel.MODERATE);
        existing.setSensitivityLevel(SensitivityLevel.MILD);

        Client updated = new Client();
        updated.setFullName("John Smith");
        updated.setAge(31);
        updated.setContactDetails("johnsmith@example.com");
        updated.setSkinType(SkinType.DRY);
        updated.setAcneLevel(AcneLevel.MILD);
        updated.setSensitivityLevel(SensitivityLevel.SEVERE);

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Client.class))).thenReturn(existing);

        Client result = service.updateClient(1L, updated);

        assertNotNull(result);
        assertEquals("John Smith", result.getFullName());
        assertEquals(31, result.getAge());
        assertEquals(SkinType.DRY, result.getSkinType());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(existing);
    }
}