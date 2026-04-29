package com.example.lab_7.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Integer idClient;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate regDate;
}
