package com.example.lab_7.controller;

import com.example.lab_7.model.Author;
import com.example.lab_7.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAll() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable int id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable int id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @PatchMapping("/{id}")
    public Author patchBio(@PathVariable int id, @RequestBody String newBio) {
        return authorService.updateAuthorBio(id, newBio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        authorService.deleteAuthor(id);
    }
}
