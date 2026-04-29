package com.example.lab_7.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    private Integer idMaterial;
    private String title;
    private String annotation;
    private String bodyText;
    private Double cost;
    private Integer idSection;
}
