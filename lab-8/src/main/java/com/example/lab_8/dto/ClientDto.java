package com.example.lab_8.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends PersonDto {
    @NotNull(message = "Registration date is necessary")
    private LocalDate regDate;
}