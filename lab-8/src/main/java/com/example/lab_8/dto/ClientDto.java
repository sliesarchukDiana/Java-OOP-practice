package com.example.lab_8.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends PersonDto {
    @NotNull(message = "Registration date is necessary")
    @PastOrPresent(message = "Are you from the future?")
    private LocalDate regDate;
}