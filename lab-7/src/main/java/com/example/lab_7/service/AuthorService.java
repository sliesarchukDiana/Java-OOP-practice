package com.example.lab_7.service;

import com.example.lab_7.model.Author;
import com.example.lab_7.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        log.info("Creating new author...");
        return authorRepository.save(author);
    }

    public Author updateAuthor(int id, Author authorDetails) {
        authorDetails.setIdPerson(id);
        log.info("Updating author with id {}", id);
        return authorRepository.save(authorDetails);
    }

    public Author updateAuthorBio(int id, String newBio) {
        Author existingAuthor = authorRepository.findById(id);
        if (existingAuthor != null) {
            existingAuthor.setBio(newBio);
            return authorRepository.save(existingAuthor);
        }
        return null;
    }

    public void deleteAuthor(int id) {
        log.info("Deleting author with id {}", id);
        authorRepository.deleteById(id);
    }
}