package com.example.lab_7.service;

import com.example.lab_7.model.Material;
import com.example.lab_7.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Optional<Material> getMaterialById(int id) {
        return materialRepository.findById(id);
    }

    public Material createMaterial(Material material) {
        log.info("Creating new material: {}", material.getTitle());
        return materialRepository.save(material);
    }

    public Optional<Material> updateMaterial(int id, Material materialDetails) {
        log.info("Updating material with id {}", id);
        return materialRepository.findById(id).map(existingMaterial -> {
            existingMaterial.setTitle(materialDetails.getTitle());
            existingMaterial.setAnnotation(materialDetails.getAnnotation());
            existingMaterial.setBodyText(materialDetails.getBodyText());
            existingMaterial.setCost(materialDetails.getCost());
            existingMaterial.setIdSection(materialDetails.getIdSection());
            return materialRepository.save(existingMaterial);
        });
    }

    public Optional<Material> patchMaterial(int id, Material materialDetails) {
        log.info("Patching material with id {}", id);
        return materialRepository.findById(id).map(existingMaterial -> {
            if (materialDetails.getTitle() != null) existingMaterial.setTitle(materialDetails.getTitle());
            if (materialDetails.getAnnotation() != null) existingMaterial.setAnnotation(materialDetails.getAnnotation());
            if (materialDetails.getBodyText() != null) existingMaterial.setBodyText(materialDetails.getBodyText());
            if (materialDetails.getCost() != null) existingMaterial.setCost(materialDetails.getCost());
            if (materialDetails.getIdSection() != null) existingMaterial.setIdSection(materialDetails.getIdSection());
            return materialRepository.save(existingMaterial);
        });
    }

    public boolean deleteMaterial(int id) {
        log.info("Deleting material with id {}", id);
        return materialRepository.deleteById(id);
    }
}