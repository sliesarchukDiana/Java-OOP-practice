package com.example.lab_8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Об'єкт розділу каталогу")
public class CatalogueSectionDto {

    @Schema(description = "Унікальний ідентифікатор розділу", example = "1")
    private Integer id;

    @Schema(description = "Назва розділу", example = "Веб-розробка")
    private String name;

    @Schema(description = "Опис розділу", example = "Статті присвячені веб-розробці")
    private String description;

    @Schema(description = "ID батьківського розділу", example = "1")
    private Integer parentId;
}