package com.example.lab_7.repository;

import  com.example.lab_7.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorRepository {

    private final Map<Integer, Author> storage = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(1);

    public List<Author> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Author> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Author save(Author author) {
        if (author.getIdPerson() == null) {
            author.setIdPerson(currentId.getAndIncrement());
        }
        storage.put(author.getIdPerson(), author);
        return author;
    }

    public boolean deleteById(int id) {return storage.remove(id) != null;}
}