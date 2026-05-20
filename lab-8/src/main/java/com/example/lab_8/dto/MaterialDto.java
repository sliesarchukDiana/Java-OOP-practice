package com.example.lab_8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Schema(description = "Модель для передачі даних матеріалу (статті)")
public class MaterialDto {

    @Schema(description = "Унікальний ідентифікатор матеріалу", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255, message = "Title is too long")
    @Schema(description = "Назва матеріалу", example = "Введення у Spring Boot та REST API", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Size(max = 500, message = "Only 500 symbols, too long!")
    @Schema(description = "Коротка анотація або опис змісту статті", example = "Базові концепції створення веб-додатків на Spring Boot, налаштування контролерів та обробка HTTP-запитів.")
    private String annotation;

    @Schema(description = "Повний текстовий вміст матеріалу", example = "Тут знаходиться детальний текст...")
    private String bodyText;

    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Price cannot be negative!")
    @Schema(description = "Вартість доступу до матеріалу (0.00 означає, що матеріал безкоштовний)", example = "150.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal cost;

    @NotNull(message = "Material must belong to some section!")
    @Schema(description = "Ідентифікатор розділу каталогу, до якого належить цей матеріал", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sectionId;

    @Schema(description = "Набір ідентифікаторів авторів, які створили цей матеріал", example = "[1, 3]")
    private Set<Integer> authorIds;

    @Schema(description = "Набір ідентифікаторів ключових слів (тегів) для пошуку", example = "[5, 8, 12]")
    private Set<Integer> keywordIds;
}