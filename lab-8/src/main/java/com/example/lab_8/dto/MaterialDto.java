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
@Schema(description = "Модель для передачі та створення навчального матеріалу")
public class MaterialDto {

    @Schema(description = "Унікальний ідентифікатор матеріалу", example = "12", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255, message = "Toooo long")
    @Schema(description = "Назва матеріалу", example = "Глибоке занурення в Spring Data JPA", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Size(max = 500, message = "Only 500 symbols, too long!")
    @Schema(description = "Короткий опис або анотація", example = "Детальний розбір роботи Hibernate, проксі-об'єктів та оптимізації SQL-запитів")
    private String annotation;

    @Schema(description = "Повний текст матеріалу", example = "Тут знаходиться дуже розумний текст лекції...")
    private String bodyText;

    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Enter normal price you dumdum!")
    @Schema(description = "Вартість доступу до матеріалу (0 - безкоштовно)", example = "250.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal cost;

    @NotNull(message = "Material must belong to some section!")
    @Schema(description = "ID розділу каталогу, до якого належить матеріал", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sectionId;

    @Schema(description = "Список ID авторів матеріалу", example = "[1, 4]")
    private Set<Integer> authorIds;

    @Schema(description = "Список ID ключових слів (тегів)", example = "[2, 5, 11]")
    private Set<Integer> keywordIds;
}