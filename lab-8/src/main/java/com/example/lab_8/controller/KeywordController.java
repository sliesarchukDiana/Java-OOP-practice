package com.example.lab_8.controller;

import com.example.lab_8.dto.KeywordDto;
import com.example.lab_8.exception.ApiErrorResponse;
import com.example.lab_8.service.KeywordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/api/keywords")
@RequiredArgsConstructor
@Tag(name = "Ключові слова", description = "Управління ключовими словами для фільтрації матеріалів")
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping
    @Operation(summary = "Отримати всі ключові слова", description = "Повертає повний перелік ключових слів, що використовуються в системі")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список ключових слів успішно отримано",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = KeywordDto.class))))
    })
    public ResponseEntity<List<KeywordDto>> getAll() {
        return ResponseEntity.ok(keywordService.getAllKeywords());
    }

    @PostMapping
    @Operation(summary = "Створити нове ключове слово", description = "Додає нове ключове слово (тег) до бази даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ключове слово створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = KeywordDto.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<KeywordDto> create(@Valid @RequestBody KeywordDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(keywordService.createKeyword(dto));
    }
}