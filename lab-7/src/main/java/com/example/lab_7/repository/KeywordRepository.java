package com.example.lab_7.repository;

import com.example.lab_7.model.Keyword;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class KeywordRepository {

    private final Map<Integer, Keyword> storage = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(1);

    public List<Keyword> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Keyword> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Keyword save(Keyword keyword) {
        if (keyword.getIdKeyword() == null) {
            keyword.setIdKeyword(currentId.getAndIncrement());
        }
        storage.put(keyword.getIdKeyword(), keyword);
        return keyword;
    }

    public boolean deleteById(int id) {
        return storage.remove(id) != null;
    }
}