package com.example.lab_8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Material")
@Data
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Integer idMaterial;

    @NotBlank(message = "Title cannot be empty")
    @Column(name = "title")
    private String title;

    @Column(name = "annotation", length = 500)
    private String annotation;

    @Column(name = "body_text", columnDefinition = "TEXT")
    private String bodyText;

    @PositiveOrZero(message = "Are u stupid?")
    @Column(name = "cost")
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section")
    private CatalogueSection section;

    @ManyToMany
    @JoinTable(
            name = "Author-Material",
            joinColumns = @JoinColumn(name = "id_material"),
            inverseJoinColumns = @JoinColumn(name = "id_author")
    )
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "Material-Keyword",
            joinColumns = @JoinColumn(name = "id_material"),
            inverseJoinColumns = @JoinColumn(name = "id_keyword")
    )
    private Set<Keyword> keywords;
}