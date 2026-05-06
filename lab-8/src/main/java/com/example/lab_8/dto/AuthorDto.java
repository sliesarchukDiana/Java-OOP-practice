package com.example.lab_8.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorDto extends PersonDto {
    private String bio;
}