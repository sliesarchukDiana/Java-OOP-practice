package com.example.lab_8.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Author")
@PrimaryKeyJoinColumn(name = "id_person")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Author extends Person {

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;
}