package com.example.lab_7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Integer idPerson;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
}
