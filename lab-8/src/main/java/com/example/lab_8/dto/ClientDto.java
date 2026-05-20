package com.example.lab_8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Модель профілю клієнта (включає всі базові поля PersonDto)")
public class ClientDto extends PersonDto {
    @Schema(description = "Дата реєстрації клієнта", example = "2026-05-19")
    @NotNull(message = "Registration date is necessary")
    @PastOrPresent(message = "Are you from the future?")
    private LocalDate regDate;
}