
package com.tinah_tunner.skin.care.profile.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinah_tunner.skin.care.profile.model.Client;
import com.tinah_tunner.skin.care.profile.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    // ➕ Create client
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return service.saveClient(client);
    }

    // 📄 Get all clients
    @GetMapping
    public List<Client> getAllClients() {
        return service.getAllClients();
    }

    // 🔍 Get client by ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return service.getClientById(id);
    }

    // ❌ Delete client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        service.deleteClient(id);
    }

    // ✏️ Update client
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return service.updateClient(id, client);
    }
}