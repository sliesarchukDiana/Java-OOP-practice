package com.example.lab_8.controller;

import com.example.lab_8.dto.MaterialDto;
import com.example.lab_8.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping
    public List<MaterialDto> getAll() {
        return materialService.getAllMaterials();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialDto create(@Valid @RequestBody MaterialDto dto) {
        return materialService.createMaterial(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        materialService.deleteMaterial(id);
    }
}