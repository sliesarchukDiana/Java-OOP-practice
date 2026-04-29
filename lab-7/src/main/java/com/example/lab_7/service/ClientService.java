package com.example.lab_7.service;

import com.example.lab_7.model.Client;
import com.example.lab_7.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        log.info("Creating new client: {} {}", client.getFirstName(), client.getLastName());
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(int id, Client clientDetails) {
        log.info("Updating client with id {}", id);
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setFirstName(clientDetails.getFirstName());
            existingClient.setLastName(clientDetails.getLastName());
            existingClient.setEmail(clientDetails.getEmail());
            if (clientDetails.getRegDate() != null) {
                existingClient.setRegDate(clientDetails.getRegDate());
            }
            return clientRepository.save(existingClient);
        });
    }

    public Optional<Client> patchClient(int id, Client clientDetails) {
        log.info("Patching client with id {}", id);
        return clientRepository.findById(id).map(existingClient -> {
            if (clientDetails.getFirstName() != null) existingClient.setFirstName(clientDetails.getFirstName());
            if (clientDetails.getLastName() != null) existingClient.setLastName(clientDetails.getLastName());
            if (clientDetails.getEmail() != null) existingClient.setEmail(clientDetails.getEmail());
            if (clientDetails.getRegDate() != null) existingClient.setRegDate(clientDetails.getRegDate());
            return clientRepository.save(existingClient);
        });
    }

    public boolean deleteClient(int id) {
        log.info("Deleting client with id {}", id);
        return clientRepository.deleteById(id);
    }
}