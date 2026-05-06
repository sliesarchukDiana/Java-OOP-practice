package com.example.lab_8.repository;

import com.example.lab_8.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    java.util.Optional<Keyword> findByWord(String word);
}