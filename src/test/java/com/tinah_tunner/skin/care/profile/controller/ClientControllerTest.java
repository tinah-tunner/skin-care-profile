package com.tinah_tunner.skin.care.profile.controller;

import com.tinah_tunner.skin.care.profile.model.AcneLevel;
import com.tinah_tunner.skin.care.profile.model.Client;
import com.tinah_tunner.skin.care.profile.model.SensitivityLevel;
import com.tinah_tunner.skin.care.profile.model.SkinType;
import com.tinah_tunner.skin.care.profile.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=none",
    "spring.datasource.url=jdbc:h2:mem:testdb"
})
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService service;

    @Test
    void testCreateClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setFullName("John Doe");
        client.setAge(30);
        client.setContactDetails("john@example.com");
        client.setSkinType(SkinType.OILY);
        client.setAcneLevel(AcneLevel.MODERATE);
        client.setSensitivityLevel(SensitivityLevel.MILD);

        when(service.saveClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fullName\":\"John Doe\",\"age\":30,\"contactDetails\":\"john@example.com\",\"skinType\":\"OILY\",\"acneLevel\":\"MODERATE\",\"sensitivityLevel\":\"MILD\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("John Doe"));
    }

    @Test
    void testGetAllClients() throws Exception {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setFullName("John Doe");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setFullName("Jane Doe");

        List<Client> clients = Arrays.asList(client1, client2);

        when(service.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fullName").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].fullName").value("Jane Doe"));
    }

    @Test
    void testGetClientById() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setFullName("John Doe");

        when(service.getClientById(1L)).thenReturn(client);

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("John Doe"));
    }

    @Test
    void testDeleteClient() throws Exception {
        doNothing().when(service).deleteClient(1L);

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());
    }
}