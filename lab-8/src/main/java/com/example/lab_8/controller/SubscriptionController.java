package com.example.lab_8.controller;

import com.example.lab_8.dto.SubscriptionDto;
import com.example.lab_8.exception.ApiErrorResponse;
import com.example.lab_8.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Підписки", description = "Управління підписками клієнтів на розділи або ключові слова")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping
    @Operation(summary = "Отримати список всіх підписок", description = "Повертає повний список активних підписок у системі")
    public ResponseEntity<List<SubscriptionDto>> getAll() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @PostMapping
    @Operation(summary = "Створити нову підписку", description = "Оформлює підписку для клієнта на основі наданих даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Підписку успішно створено"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації даних",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<SubscriptionDto> create(@Valid @RequestBody SubscriptionDto dto) {
        SubscriptionDto created = subscriptionService.createSubscription(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}