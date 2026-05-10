package com.example.lab_8.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class MaterialDto {
    private Integer id;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255, message = "Toooo long")
    private String title;

    @Size(max = 500, message = "Only  500 symbols, too long!")
    private String annotation;

    private String bodyText;

    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Enter normal price you dumdum!")
    private BigDecimal cost;

    @NotNull(message = "Material must belong to some section!")
    private Integer sectionId;

    private Set<Integer> authorIds;
    private Set<Integer> keywordIds;
}