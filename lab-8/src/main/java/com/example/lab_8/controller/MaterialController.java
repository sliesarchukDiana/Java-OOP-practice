package com.example.lab_8.controller;

import com.example.lab_8.dto.MaterialDto;
import com.example.lab_8.exception.ApiErrorResponse;
import com.example.lab_8.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
@Tag(name = "Матеріали", description = "Ендпоінти для створення, пагінації та управління навчальними матеріалами")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    @Operation(summary = "Отримати матеріали з пагінацією", description = "Повертає сторінку матеріалів відповідно до параметрів page, size та sort")
    public ResponseEntity<Page<MaterialDto>> getMaterialsPaginated(
            @Parameter(description = "Параметри пагінації (напр. page=0&size=10)") Pageable pageable) {
        return ResponseEntity.ok(materialService.getMaterialsPaginated(pageable));
    }

    @PostMapping
    @Operation(summary = "Створити новий матеріал", description = "Приймає дані матеріалу, валідує їх та прив'язує до сутностей через проксі-об'єкти")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Матеріал успішно створено",
                    content = @Content(schema = @Schema(implementation = MaterialDto.class))),
            @ApiResponse(responseCode = "400", description = "Некоректні вхідні дані (помилка валідації)",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Конфлікт у базі даних (наприклад, вказано неіснуючий sectionId)",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<MaterialDto> createMaterial(@Valid @RequestBody MaterialDto materialDto) {
        return ResponseEntity.ok(materialService.createMaterial(materialDto));
    }
}