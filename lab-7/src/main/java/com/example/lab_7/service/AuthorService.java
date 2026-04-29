package com.example.lab_7.service;

import com.example.lab_7.model.Author;
import com.example.lab_7.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        log.info("Creating new author: {} {}", author.getFirstName(), author.getLastName());
        return authorRepository.save(author);
    }

    public Optional<Author> updateAuthor(int id, Author authorDetails) {
        log.info("Updating author with id {}", id);
        return authorRepository.findById(id).map(existingAuthor -> {
            existingAuthor.setFirstName(authorDetails.getFirstName());
            existingAuthor.setLastName(authorDetails.getLastName());
            existingAuthor.setEmail(authorDetails.getEmail());
            existingAuthor.setBio(existingAuthor.getBio());
            return authorRepository.save(existingAuthor);
        });
    }

    public Optional<Author> patchAuthor (int id, Author authorDetails){
        log.info("Patching author with id {}", id);
        return authorRepository.findById(id).map(existingAuthor -> {
            if (authorDetails.getFirstName() != null) existingAuthor.setFirstName(authorDetails.getFirstName());
            if (authorDetails.getLastName() != null) existingAuthor.setLastName(authorDetails.getLastName());
            if (authorDetails.getEmail() != null) existingAuthor.setEmail(authorDetails.getEmail());
            if (authorDetails.getBio() != null) existingAuthor.setBio(authorDetails.getBio());
            return authorRepository.save(existingAuthor);
        });
    }

    public boolean deleteAuthor(int id) {
        log.info("Deleting author with id {}", id);
        return authorRepository.deleteById(id);
    }
}