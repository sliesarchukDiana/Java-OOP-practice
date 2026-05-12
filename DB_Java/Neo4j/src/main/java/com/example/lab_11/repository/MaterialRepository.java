package com.example.lab_11.repository;

import com.example.lab_11.entity.Material;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends Neo4jRepository<Material, Long> {
}