package com.example.lab_7.repository;

import com.example.lab_7.model.Material;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MaterialRepository {

    private final Map<Integer, Material> storage = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(1);

    public List<Material> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Material> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Material save(Material material) {
        if (material.getIdMaterial() == null) {
            material.setIdMaterial(currentId.getAndIncrement());
        }
        storage.put(material.getIdMaterial(), material);
        return material;
    }

    public boolean deleteById(int id) {
        return storage.remove(id) != null;
    }
}