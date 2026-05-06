package com.example.lab_8.controller;

import com.example.lab_8.dto.CatalogueSectionDto;
import com.example.lab_8.service.CatalogueSectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class CatalogueSectionController {
    private final CatalogueSectionService sectionService;

    @GetMapping
    public List<CatalogueSectionDto> getAll() {
        return sectionService.getAllSections();
    }

    @PostMapping
    public CatalogueSectionDto create(@Valid @RequestBody CatalogueSectionDto dto) {
        return sectionService.createSection(dto);
    }
}