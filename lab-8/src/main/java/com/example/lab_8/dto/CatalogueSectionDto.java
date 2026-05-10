package com.example.lab_8.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CatalogueSectionDto {
    private Integer id;

    @NotBlank(message = "Section name is necessary")
    @Size(max = 100, message = "Too long name")
    private String name;

    private Integer parentId;
}