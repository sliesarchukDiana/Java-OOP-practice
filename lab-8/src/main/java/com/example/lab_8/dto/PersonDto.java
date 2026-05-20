package com.example.lab_8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Базова модель користувача системи (успадковується авторами та клієнтами)")
public class PersonDto {

    @Schema(description = "Унікальний ідентифікатор", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank(message = "First name cannot be empty")
    @Size(max = 100, message = "The name is too long")
    @Schema(description = "Ім'я", example = "Раян", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 45, message = "Too long for the last name!")
    @Schema(description = "Прізвище", example = "Рейнольдс", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Incorrect email format")
    @Size(max = 100, message = "Email is too long")
    @Schema(description = "Контактна електронна адреса", example = "rian.reynolds@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}