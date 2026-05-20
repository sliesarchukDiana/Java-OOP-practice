package com.example.lab_8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Keyword")
@Getter
@Setter
@NoArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_keyword")
    private Integer idKeyword;

    @NotBlank
    @Column(name = "word", length = 45, unique = true)
    private String word;
}