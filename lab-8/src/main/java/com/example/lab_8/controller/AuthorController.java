package com.example.lab_8.controller;

import com.example.lab_8.dto.AuthorDto;
import com.example.lab_8.exception.ApiErrorResponse;
import com.example.lab_8.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Tag(name = "Автори", description = "Управління профілями авторів (створення, оновлення, видалення)")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    @Operation(
            summary = "Отримати список всіх авторів",
            description = "Повертає повний список авторів, зареєстрованих у системі"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список авторів успішно отримано",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AuthorDto.class)))
            )
    })
    public ResponseEntity<List<AuthorDto>> getAll() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Знайти автора за ID",
            description = "Повертає детальну інформацію про автора за його унікальним числовим ідентифікатором"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Автора успішно знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Автора з таким ID не знайдено в системі",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<AuthorDto> getById(
            @Parameter(description = "Унікальний ідентифікатор автора", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping
    @Operation(
            summary = "Створити нового автора",
            description = "Створює новий профіль автора на основі переданих валідованих даних (ім'я, email, біографія)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Автора успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Помилка валідації вхідних даних або некоректний формат JSON",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<AuthorDto> create(@Valid @RequestBody AuthorDto authorDto) {
        AuthorDto created = authorService.createAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Оновити дані автора",
            description = "Повністю оновлює дані існуючого автора за його ідентифікатором на основі переданого тіла запиту"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Дані автора успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Помилка валідації вхідних даних",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Автора для оновлення не знайдено за вказаним ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<AuthorDto> update(
            @Parameter(description = "ID автора для оновлення", example = "1") @PathVariable Integer id,
            @Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorDto));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Видалити автора",
            description = "Видаляє профіль автора та пов'язані дані за його унікальним ідентифікатором"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Автора успішно видалено або він відсутній у системі"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфлікт цілісності даних у базі (наприклад, автор пов'язаний із чинними публікаціями)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID автора для видалення", example = "1") @PathVariable Integer id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/full-name")
    @Operation(
            summary = "Отримати повне ім'я автора",
            description = "Формує та повертає текстовий рядок із повним ім'ям та прізвищем автора"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Повне ім'я успішно згенеровано",
                    content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class, example = "Діана Слєсарчук"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Автора не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<String> getFullName(
            @Parameter(description = "ID автора", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(authorService.getAuthorFullNameLegacy(id));
    }
}