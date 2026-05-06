package com.example.lab_8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Person")
    private Integer idPerson;

    @NotBlank(message = "First name is necessary")
    @Size(max = 100)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is necessary")
    @Size(max = 45)
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Incorrect email format")
    @NotBlank(message = "Email is necessary")
    @Size(max = 100)
    @Column(name = "email", unique = true)
    private String email;
}