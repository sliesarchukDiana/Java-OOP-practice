package com.example.lab_8.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CatalogueSectionDto {
    private Integer id;

    @NotBlank(message = "Section name is required!")
    private String name;

    private Integer parentId;
}