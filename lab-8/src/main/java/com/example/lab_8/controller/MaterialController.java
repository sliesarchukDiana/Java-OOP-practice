package com.example.lab_8.controller;

import com.example.lab_8.dto.MaterialDto;
import com.example.lab_8.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/problem")
    public ResponseEntity<String> demonstrateNPlusOne() {
        materialService.getAllMaterialsWithNPlusOne();
        return ResponseEntity.ok("Pile of SQL shit");
    }

    @GetMapping("/fixed")
    public ResponseEntity<String> demonstrateNPlusOneFixed() {
        materialService.getAllMaterialsFixed();
        return ResponseEntity.ok("Normal request");
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<MaterialDto>> getMaterialsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(materialService.getMaterialsPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<MaterialDto> createMaterial(@Valid @RequestBody MaterialDto materialDto) {
        return ResponseEntity.ok(materialService.createMaterial(materialDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}