package com.example.lab_8.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDto {
    private Integer id;

    @NotBlank(message = "First name cannot be empty!")
    @Size(max = 100, message = "Is it your legal name?")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty!")
    @Size(max = 45, message = "Too long for the last name!")
    private String lastName;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Incorrect email format!")
    @Size(max = 100, message = "Email is tooooooooo long")
    private String email;
}