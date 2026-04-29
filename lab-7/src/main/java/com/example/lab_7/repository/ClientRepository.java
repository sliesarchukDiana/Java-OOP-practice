package com.example.lab_7.repository;

import com.example.lab_7.model.Client;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ClientRepository {

    private final Map<Integer, Client> storage = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(1);

    public List<Client> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Client> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            client.setIdClient(currentId.getAndIncrement());
        }
        if (client.getRegDate() == null) {
            client.setRegDate(LocalDate.now());
        }
        storage.put(client.getIdClient(), client);
        return client;
    }

    public boolean deleteById(int id) {
        return storage.remove(id) != null;
    }
}