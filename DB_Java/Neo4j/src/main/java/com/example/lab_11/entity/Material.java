package com.example.lab_11.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Material")
@Data
public class Material {
    @Id
    private Long id;
    private String title;
    private Double cost;
    private String body_text;
}