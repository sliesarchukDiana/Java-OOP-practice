package com.example.lab_7.service;

import com.example.lab_7.model.Keyword;
import com.example.lab_7.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public List<Keyword> getAllKeywords() {
        return keywordRepository.findAll();
    }

    public Optional<Keyword> getKeywordById(int id) {
        return keywordRepository.findById(id);
    }

    public Keyword createKeyword(Keyword keyword) {
        log.info("Creating new keyword: {}", keyword.getWord());
        return keywordRepository.save(keyword);
    }

    public Optional<Keyword> updateKeyword(int id, Keyword keywordDetails) {
        log.info("Updating keyword with id {}", id);
        return keywordRepository.findById(id).map(existingKeyword -> {
            existingKeyword.setWord(keywordDetails.getWord());
            return keywordRepository.save(existingKeyword);
        });
    }

    public Optional<Keyword> patchKeyword(int id, Keyword keywordDetails) {
        log.info("Patching keyword with id {}", id);
        return keywordRepository.findById(id).map(existingKeyword -> {
            if (keywordDetails.getWord() != null) {
                existingKeyword.setWord(keywordDetails.getWord());
            }
            return keywordRepository.save(existingKeyword);
        });
    }

    public boolean deleteKeyword(int id) {
        log.info("Deleting keyword with id {}", id);
        return keywordRepository.deleteById(id);
    }
}