package com.example.lab_8.service;

import com.example.lab_8.dto.CatalogueSectionDto;
import com.example.lab_8.mapper.CatalogueSectionMapper;
import com.example.lab_8.model.CatalogueSection;
import com.example.lab_8.repository.CatalogueSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogueSectionService {
    private final CatalogueSectionRepository sectionRepository;
    private final CatalogueSectionMapper sectionMapper;

    public List<CatalogueSectionDto> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(sectionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CatalogueSectionDto createSection(CatalogueSectionDto dto) {
        CatalogueSection section = sectionMapper.toEntity(dto);
        if (dto.getParentId() != null) {
            section.setParent(sectionRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent section not found")));
        }
        return sectionMapper.toDto(sectionRepository.save(section));
    }
}