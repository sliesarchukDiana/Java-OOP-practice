package com.example.lab_8.repository;

import com.example.lab_8.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    @Query("SELECT DISTINCT m FROM Material m " +
            "LEFT JOIN FETCH m.authors " +
            "LEFT JOIN FETCH m.keywords")
    List<Material> findAllWithAuthors();
}