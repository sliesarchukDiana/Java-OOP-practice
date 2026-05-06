package com.example.lab_8.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "Catalogue_section")
@Data
@NoArgsConstructor
public class CatalogueSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_section")
    private Integer idSection;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CatalogueSection parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CatalogueSection> subSections;
}