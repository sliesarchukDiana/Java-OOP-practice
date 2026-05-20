package com.example.lab_8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Об'єкт підписки")
public class SubscriptionDto {

    @Schema(description = "Унікальний ідентифікатор підписки", example = "1")
    private Integer id;

    @NotNull(message = "Format type is necessary!")
    @Schema(description = "Тип формату підписки", example = "1")
    private Integer formatType;

    @NotNull(message = "Client's ID is required!")
    @Schema(description = "ID клієнта, який оформлює підписку", example = "1")
    private Integer clientId;

    @Schema(description = "ID розділу каталогу (опціонально)", example = "1")
    private Integer sectionId;

    @Schema(description = "ID ключового слова (опціонально)", example = "1")
    private Integer keywordId;
}