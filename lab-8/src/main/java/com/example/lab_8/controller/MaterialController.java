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
@Tag(name = "Матеріали", description = "Управління матеріалами та публікаціями")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    @Operation(
            summary = "Отримати список матеріалів з пагінацією",
            description = "Дозволяє завантажувати матеріали сторінками із можливістю сортування. Запобігає перевантаженню пам'яті."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сторінку з матеріалами успішно отримано")
    })
    public ResponseEntity<Page<MaterialDto>> getMaterialsPaginated(
            @Parameter(
                    description = "Параметри пагінації та сортування (наприклад: ?page=0&size=10&sort=cost,desc)",
                    example = "{\"page\": 0, \"size\": 10}"
            ) Pageable pageable) {
        return ResponseEntity.ok(materialService.getMaterialsPaginated(pageable));
    }

    @PostMapping
    @Operation(
            summary = "Створити новий матеріал",
            description = "Валідує вхідний JSON та створює запис матеріалу, зв'язуючи його з розділом, авторами та тегами за допомогою Hibernate."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Матеріал успішно створено у каталозі",
                    content = @Content(schema = @Schema(implementation = MaterialDto.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Помилка валідації полів (наприклад, від'ємна вартість або порожня назва)",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409", description = "Конфлікт цілісності даних (вказано неіснуючий ідентифікатор розділу чи автора)",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<MaterialDto> createMaterial(@Valid @RequestBody MaterialDto materialDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.createMaterial(materialDto));
    }
}