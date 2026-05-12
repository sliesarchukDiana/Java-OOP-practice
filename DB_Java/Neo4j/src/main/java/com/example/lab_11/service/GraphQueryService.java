package com.example.lab_11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GraphQueryService {

    private final Neo4jClient neo4jClient;

    public void executeAllQueries() {
        System.out.println("Materials that have > 1 authors:");
        String query1 = "MATCH (a:Author)-[:WROTE]->(m:Material) WITH m, count(a) AS author_count WHERE author_count > 1 RETURN m.title AS title, author_count";
        printResults(neo4jClient.query(query1).fetch().all());

        System.out.println("\n'Programming' and its subsections:");
        String query2 = "MATCH (s:Catalogue_section {name: 'Programming'})-[:PARENT_OF*0..1]->(sub_s:Catalogue_section) MATCH (m:Material)-[:BELONGS_TO]->(sub_s) RETURN m.title AS title, sub_s.name AS section";
        printResults(neo4jClient.query(query2).fetch().all());

        System.out.println("\nUsers who are subscribed Elijah Kamski's materials:");
        String query3 = "MATCH (:Person {first_name: 'Elijah'})-[:WROTE]->(m:Material)-[:BELONGS_TO]->(s:Catalogue_section) MATCH (subscriber:Client)-[:SUBSCRIBED_TO]->(s) RETURN DISTINCT subscriber.first_name AS fname, subscriber.last_name AS lname, s.name AS section";
        printResults(neo4jClient.query(query3).fetch().all());

        System.out.println("\nThe most popular section:");
        String query4 = "MATCH (c:Client)-[:SUBSCRIBED_TO]->(s:Catalogue_section) RETURN s.name AS section, count(c) AS subscriptions ORDER BY subscriptions DESC LIMIT 1";
        printResults(neo4jClient.query(query4).fetch().all());

        System.out.println("\nMaterials and authors 'Algorithms':");
        String query5 = "MATCH (k:Keyword {word: 'Algorithms'})<-[:HAS_KEYWORD]-(m:Material)<-[:WROTE]-(a:Author) RETURN m.title AS title, a.first_name + ' ' + a.last_name AS author_name";
        printResults(neo4jClient.query(query5).fetch().all());
    }

    private void printResults(Collection<Map<String, Object>> results) {
        if (results.isEmpty()) {
            System.out.println("Nothing found.");
        } else {
            results.forEach(row -> System.out.println("  -> " + row));
        }
    }
}