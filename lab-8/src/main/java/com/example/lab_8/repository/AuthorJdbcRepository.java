package com.example.lab_8.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthorJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public String getFullName(Integer personId) {
        String sql = "SELECT get_person_fullname(?)";
        return jdbcTemplate.queryForObject(sql, String.class, personId);
    }

    public Double getAuthorTotalCost(Integer authorId) {
        String sql = "SELECT get_author_total_cost(?)";
        return jdbcTemplate.queryForObject(sql, Double.class, authorId);
    }
}