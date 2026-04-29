package com.example.lab_7.repository;

import com.example.lab_7.model.Section;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SectionRepository {

    private final Map<Integer, Section> storage = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(1);

    public List<Section> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Section> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Section save(Section section) {
        if (section.getIdSection() == null) {
            section.setIdSection(currentId.getAndIncrement());
        }
        storage.put(section.getIdSection(), section);
        return section;
    }

    public boolean deleteById(int id) {
        return storage.remove(id) != null;
    }
}