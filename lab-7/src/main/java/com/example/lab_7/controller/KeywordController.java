package com.example.lab_7.controller;

import com.example.lab_7.model.Keyword;
import com.example.lab_7.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping
    public ResponseEntity<List<Keyword>> getAll() {
        return ResponseEntity.ok(keywordService.getAllKeywords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Keyword> getById(@PathVariable int id) {
        return keywordService.getKeywordById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Keyword> create(@RequestBody Keyword keyword) {
        Keyword created = keywordService.createKeyword(keyword);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Keyword> update(@PathVariable int id, @RequestBody Keyword keyword) {
        return keywordService.updateKeyword(id, keyword)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Keyword> patch(@PathVariable int id, @RequestBody Keyword keyword) {
        return keywordService.patchKeyword(id, keyword)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (keywordService.deleteKeyword(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}