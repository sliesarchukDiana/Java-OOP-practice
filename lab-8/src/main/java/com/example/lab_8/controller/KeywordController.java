package com.example.lab_8.controller;

import com.example.lab_8.dto.KeywordDto;
import com.example.lab_8.service.KeywordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
@RequiredArgsConstructor
public class KeywordController {
    private final KeywordService keywordService;

    @GetMapping
    public List<KeywordDto> getAll() {
        return keywordService.getAllKeywords();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KeywordDto create(@Valid @RequestBody KeywordDto dto) {
        return keywordService.createKeyword(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        keywordService.deleteKeyword(id);
    }
}