package com.example.lab_8.service;

import com.example.lab_8.dto.MaterialDto;
import com.example.lab_8.mapper.MaterialMapper;
import com.example.lab_8.model.Author;
import com.example.lab_8.model.Keyword;
import com.example.lab_8.model.Material;
import com.example.lab_8.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final AuthorRepository authorRepository;
    private final KeywordRepository keywordRepository;
    private final CatalogueSectionRepository sectionRepository;
    private final MaterialMapper materialMapper;

    @Transactional(readOnly = true)
    public List<MaterialDto> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(materialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MaterialDto> getAllMaterialsWithNPlusOne() {
        List<Material> materials = materialRepository.findAll();
        for (Material material : materials) {
            material.getAuthors().size();
            material.getKeywords().size();
        }
        return materials.stream()
                .map(materialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MaterialDto> getAllMaterialsFixed() {
        List<Material> materials = materialRepository.findAllWithAuthors();
        return materials.stream()
                .map(materialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<MaterialDto> getMaterialsPaginated(Pageable pageable) {
        Page<Material> materialPage = materialRepository.findAll(pageable);
        return materialPage.map(materialMapper::toDto);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MaterialDto createMaterial(MaterialDto dto) {
        Material material = materialMapper.toEntity(dto);

        material.setSection(sectionRepository.getReferenceById(dto.getSectionId()));

        if (dto.getAuthorIds() != null) {
            Set<Author> proxyAuthors = dto.getAuthorIds().stream()
                    .map(authorRepository::getReferenceById)
                    .collect(Collectors.toSet());
            material.setAuthors(proxyAuthors);
        }

        if (dto.getKeywordIds() != null) {
            Set<Keyword> proxyKeywords = dto.getKeywordIds().stream()
                    .map(keywordRepository::getReferenceById)
                    .collect(Collectors.toSet());
            material.setKeywords(proxyKeywords);
        }

        return materialMapper.toDto(materialRepository.save(material));
    }

    @Transactional
    public void deleteMaterial(Integer id) {
        materialRepository.deleteById(id);
    }
}