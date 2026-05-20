package com.example.lab_8.controller;

import com.example.lab_8.dto.CatalogueSectionDto;
import com.example.lab_8.exception.ApiErrorResponse;
import com.example.lab_8.service.CatalogueSectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
@Tag(name = "Розділи каталогу", description = "Управління категоріями та розділами матеріалів")
public class CatalogueSectionController {

    private final CatalogueSectionService sectionService;

    @GetMapping
    @Operation(summary = "Отримати всі розділи", description = "Повертає список усіх доступних розділів каталогу")
    public ResponseEntity<List<CatalogueSectionDto>> getAll() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Знайти розділ за ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Розділ знайдено"),
            @ApiResponse(responseCode = "404", description = "Розділ не знайдено",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<CatalogueSectionDto> getById(
            @Parameter(description = "ID розділу", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(sectionService.getSectionById(id));
    }

    @PostMapping
    @Operation(summary = "Створити розділ", description = "Додає новий розділ до каталогу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Розділ успішно створено"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації")
    })
    public ResponseEntity<CatalogueSectionDto> create(@Valid @RequestBody CatalogueSectionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sectionService.createSection(dto));
    }
    
}