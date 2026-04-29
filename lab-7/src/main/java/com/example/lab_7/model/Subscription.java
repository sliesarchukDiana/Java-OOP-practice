package com.example.lab_7.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private Integer idSubscription;
    private Integer formatType;
    private Integer idClient;
    private Integer idSection;
    private Integer idKeyword;
}
