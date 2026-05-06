package com.example.lab_8.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "id_person")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Client extends Person {

    @Column(name = "reg_date")
    private LocalDate regDate;
}