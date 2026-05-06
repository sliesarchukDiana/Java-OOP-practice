package com.example.lab_8.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class MaterialDto {
    private Integer id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String annotation;
    private String bodyText;

    @PositiveOrZero(message = "Price cannot be < 0")
    private BigDecimal cost;

    @NotNull(message = "Material should belong to some section")
    private Integer sectionId;

    private Set<Integer> authorIds;
    private Set<Integer> keywordIds;
}