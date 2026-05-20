package com.example.lab_8.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "id_person")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Client extends Person {

    @Column(name = "reg_date")
    private LocalDate regDate;
}