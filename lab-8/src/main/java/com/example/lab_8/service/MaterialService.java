package com.example.lab_8.service;

import com.example.lab_8.dto.MaterialDto;
import com.example.lab_8.mapper.MaterialMapper;
import com.example.lab_8.model.Material;
import com.example.lab_8.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final AuthorRepository authorRepository;
    private final KeywordRepository keywordRepository;
    private final CatalogueSectionRepository sectionRepository;
    private final MaterialMapper materialMapper;

    public List<MaterialDto> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(materialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MaterialDto createMaterial(MaterialDto dto) {
        Material material = materialMapper.toEntity(dto);

        material.setSection(sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found")));

        if (dto.getAuthorIds() != null) {
            material.setAuthors(new HashSet<>(authorRepository.findAllById(dto.getAuthorIds())));
        }

        if (dto.getKeywordIds() != null) {
            material.setKeywords(new HashSet<>(keywordRepository.findAllById(dto.getKeywordIds())));
        }

        return materialMapper.toDto(materialRepository.save(material));
    }

    @Transactional
    public void deleteMaterial(Integer id) {
        materialRepository.deleteById(id);
    }
}