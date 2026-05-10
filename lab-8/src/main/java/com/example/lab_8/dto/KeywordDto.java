package com.example.lab_8.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class KeywordDto {
    private Integer id;

    @NotBlank(message = "Keyword cannot be empty!")
    @Size(max = 45, message = "Too long!!!")
    private String word;
}