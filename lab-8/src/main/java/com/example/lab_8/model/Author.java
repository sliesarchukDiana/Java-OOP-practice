package com.example.lab_8.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Author")
@PrimaryKeyJoinColumn(name = "id_person")
@Getter
@Setter
@NoArgsConstructor
public class Author extends Person {
    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;
}