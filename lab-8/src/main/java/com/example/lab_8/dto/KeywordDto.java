package com.example.lab_8.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KeywordDto {
    private Integer id;

    @NotBlank(message = "Keyword cannot be empty")
    private String word;
}