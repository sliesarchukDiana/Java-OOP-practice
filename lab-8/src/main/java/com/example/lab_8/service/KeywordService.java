package com.example.lab_8.service;

import com.example.lab_8.dto.KeywordDto;
import com.example.lab_8.model.Keyword;
import com.example.lab_8.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    private KeywordDto mapToDto(Keyword keyword) {
        KeywordDto dto = new KeywordDto();
        dto.setId(keyword.getIdKeyword());
        dto.setWord(keyword.getWord());
        return dto;
    }

    private Keyword mapToEntity(KeywordDto dto) {
        Keyword keyword = new Keyword();
        keyword.setIdKeyword(dto.getId());
        keyword.setWord(dto.getWord());
        return keyword;
    }

    public List<KeywordDto> getAllKeywords() {
        return keywordRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public KeywordDto createKeyword(KeywordDto dto) {
        Keyword keyword = mapToEntity(dto);
        Keyword saved = keywordRepository.save(keyword);
        return mapToDto(saved);
    }

    @Transactional
    public void deleteKeyword(Integer id) {
        keywordRepository.deleteById(id);
    }
}