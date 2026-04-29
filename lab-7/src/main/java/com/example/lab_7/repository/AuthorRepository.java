package com.example.lab_7.repository;

import  com.example.lab_7.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuthorRepository {

    private final Map<Integer, Author> storage = new ConcurrentHashMap<>();
    private int currentId = 1;

    public List<Author> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Author findById(int id) {
        return storage.get(id);
    }

    public Author save(Author author) {
        if (author.getIdPerson() == 0) {
            author.setIdPerson(currentId++);
        }
        storage.put(author.getIdPerson(), author);
        return author;
    }

    public void deleteById(int id) {
        storage.remove(id);
    }
}