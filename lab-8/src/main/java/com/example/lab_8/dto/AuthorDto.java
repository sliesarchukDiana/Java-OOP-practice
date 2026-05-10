package com.example.lab_8.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorDto extends PersonDto {
    @Size(max = 2000, message = "2000 symbols at max, sorry")
    private String bio;
}