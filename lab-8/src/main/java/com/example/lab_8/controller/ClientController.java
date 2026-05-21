package com.example.lab_8.controller;

import com.example.lab_8.dto.ClientDto;
import com.example.lab_8.exception.ApiErrorResponse;
import com.example.lab_8.service.ClientService;
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
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Клієнти", description = "Ендпоінти для управління профілями клієнтів (читачами розсилок)")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @Operation(
            summary = "Отримати всіх клієнтів",
            description = "Повертає повний список усіх зареєстрованих клієнтів системи без пагінації"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список клієнтів успішно отримано",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))
            )
    })
    public ResponseEntity<List<ClientDto>> getAll() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Знайти клієнта за ID",
            description = "Повертає розгорнуту інформацію про клієнта за його унікальним числовим ідентифікатором"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Клієнта успішно знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Клієнта з таким ID не існує в системі",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ClientDto> getById(
            @Parameter(description = "Унікальний ID клієнта", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping
    @Operation(
            summary = "Зареєструвати нового клієнта",
            description = "Створює новий профіль клієнта в базі даних. Валідує унікальність email та правильність заповнення обов'язкових полів."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Клієнта успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Помилка валідації вхідних даних (наприклад, некоректний формат email або майбутня дата реєстрації)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфлікт даних (вказана електронна адреса вже зайнята іншим користувачем)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto clientDto) {
        ClientDto created = clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Оновити дані клієнта",
            description = "Повністю оновлює інформацію про існуючого клієнта за його унікальним ідентифікатором на основі переданого тіла запиту"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Дані клієнта успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некоректні або невалідні дані для оновлення профілю",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Клієнта для оновлення не знайдено за вказаним ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ClientDto> update(
            @Parameter(description = "ID клієнта для оновлення", example = "1") @PathVariable Integer id,
            @Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.updateClient(id, clientDto));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Видалити клієнта",
            description = "Видаляє профіль клієнта з системи за його ідентифікатором. Скасовує всі пов'язані підписки."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Клієнта успішно видалено з системи (або запис уже був відсутній)"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Не вдалося видалити через конфлікт цілісності даних у базі",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID клієнта для видалення", example = "1") @PathVariable Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}