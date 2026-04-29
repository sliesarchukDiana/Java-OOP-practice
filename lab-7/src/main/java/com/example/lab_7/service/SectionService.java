package com.example.lab_7.service;

import com.example.lab_7.model.Section;
import com.example.lab_7.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Optional<Section> getSectionById(int id) {
        return sectionRepository.findById(id);
    }

    public Section createSection(Section section) {
        log.info("Creating new catalogue section: {}", section.getName());
        return sectionRepository.save(section);
    }

    public Optional<Section> updateSection(int id, Section sectionDetails) {
        log.info("Updating catalogue section with id {}", id);
        return sectionRepository.findById(id).map(existingSection -> {
            existingSection.setName(sectionDetails.getName());
            existingSection.setParentId(sectionDetails.getParentId());
            return sectionRepository.save(existingSection);
        });
    }

    public Optional<Section> patchSection(int id, Section sectionDetails) {
        log.info("Patching catalogue section with id {}", id);
        return sectionRepository.findById(id).map(existingSection -> {
            if (sectionDetails.getName() != null) {
                existingSection.setName(sectionDetails.getName());
            }
            if (sectionDetails.getParentId() != null) {
                existingSection.setParentId(sectionDetails.getParentId());
            }
            return sectionRepository.save(existingSection);
        });
    }

    public boolean deleteSection(int id) {
        log.info("Deleting catalogue section with id {}", id);
        return sectionRepository.deleteById(id);
    }
}