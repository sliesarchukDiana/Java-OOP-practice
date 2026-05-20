package com.example.lab_8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Модель автора матеріалів (включає всі поля PersonDto)")
public class AuthorDto extends PersonDto {

    @Size(max = 2000, message = "2000 symbols at max")
    @Schema(description = "Коротка біографія автора, його регалії та досягнення", example = "Мегакрутий бро який грав дедпула")
    private String bio;
}