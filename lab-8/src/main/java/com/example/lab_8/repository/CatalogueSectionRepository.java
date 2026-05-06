package com.example.lab_8.repository;

import com.example.lab_8.model.CatalogueSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueSectionRepository extends JpaRepository<CatalogueSection, Integer> {
}