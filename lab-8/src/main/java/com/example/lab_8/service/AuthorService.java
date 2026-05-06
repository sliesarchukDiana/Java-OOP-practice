package com.example.lab_8.service;

import com.example.lab_8.dto.AuthorDto;
import com.example.lab_8.mapper.AuthorMapper;
import com.example.lab_8.model.Author;
import com.example.lab_8.repository.AuthorJdbcRepository;
import com.example.lab_8.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorJdbcRepository authorJdbcRepository;

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    public AuthorDto getAuthorById(Integer id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Transactional
    public AuthorDto createAuthor(AuthorDto dto) {
        Author author = authorMapper.toEntity(dto);
        Author saved = authorRepository.save(author);
        return authorMapper.toDto(saved);
    }

    @Transactional
    public AuthorDto updateAuthor(Integer id, AuthorDto authorDetails) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        authorMapper.updateEntityFromDto(authorDetails, existingAuthor);

        Author savedAuthor = authorRepository.save(existingAuthor);
        return authorMapper.toDto(savedAuthor);
    }

    public String getAuthorFullNameLegacy(Integer id) {
        return authorJdbcRepository.getFullName(id);
    }

    @Transactional
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }
}