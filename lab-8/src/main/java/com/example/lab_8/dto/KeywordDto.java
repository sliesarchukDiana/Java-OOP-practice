package com.example.lab_8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Об'єкт ключового слова")
public class KeywordDto {

    @Schema(description = "Унікальний ідентифікатор", example = "1")
    private Integer id;

    @Schema(description = "Текст ключового слова", example = "Java")
    @NotBlank(message = "Keyword cannot be empty!")
    @Size(max = 45, message = "Too long!!!")
    private String word;
}