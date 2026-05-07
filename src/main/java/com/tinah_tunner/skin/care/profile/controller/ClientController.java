package com.tinah_tunner.skin.care.profile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.tinah_tunner.skin.care.profile.model.Client;
import com.tinah_tunner.skin.care.profile.service.ClientService;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    // CREATE CLIENT
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {

        Client savedClient = service.saveClient(client);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedClient);
    }

    // GET ALL CLIENTS
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {

        List<Client> clients = service.getAllClients();

        return ResponseEntity.ok(clients);
    }

    // GET CLIENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {

        Client client = service.getClientById(id);

        return ResponseEntity.ok(client);
    }

    // UPDATE CLIENT
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable Long id,
            @RequestBody Client client) {

        Client updatedClient = service.updateClient(id, client);

        return ResponseEntity.ok(updatedClient);
    }

    // DELETE CLIENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable Long id) {

        service.deleteClient(id);

        return ResponseEntity.ok(
                Map.of("message", "Client deleted successfully")
        );
    }

    // INVALID ID HANDLER
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch() {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error",
                        "Invalid ID format"
                ));
    }

    // GENERAL ERROR HANDLER
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(
            RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error",
                        ex.getMessage()
                ));
    }
}