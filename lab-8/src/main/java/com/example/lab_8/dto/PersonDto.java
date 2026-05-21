package com.example.lab_8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Базова модель профілю особи")
public class PersonDto {

    @Schema(description = "Унікальний числовий ідентифікатор особи", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank(message = "First name cannot be blank")
    @Schema(description = "Ім'я особи", example = "Раян")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Schema(description = "Прізвище особи", example = "Рейнольдс")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    @Schema(description = "Електронна адреса", example = "ryan.reynolds@example.com")
    private String email;
}