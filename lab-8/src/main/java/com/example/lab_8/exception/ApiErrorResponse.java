package com.example.lab_8.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@Schema(description = "Стандартизована модель відповіді при виникненні помилки")
public class ApiErrorResponse {
    @Schema(description = "HTTP статус код", example = "400")
    private int status;
    @Schema(description = "Тип помилки", example = "Validation Failed")
    private String error;
    @Schema(description = "Деталізоване повідомлення про проблему", example = "Дані не пройшли валідацію")
    private String message;
    @Schema(description = "Ендпоінт, на якому сталася помилка", example = "/api/materials")
    private String path;
    @Schema(description = "Час виникнення помилки")
    private LocalDateTime timestamp;
    @Schema(description = "Карта помилок валідації окремих полів (за наявності)", example = "{\"attribute\": \"Cannot be empty!\"}")
    private Map<String, String> validationErrors;
}