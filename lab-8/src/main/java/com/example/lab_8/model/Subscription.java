package com.example.lab_8.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Subscription")
@Data
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subscription")
    private Integer idSubscription;

    @Column(name = "format_type")
    private Integer formatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section")
    private CatalogueSection section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_keyword")
    private Keyword keyword;
}