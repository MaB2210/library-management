package com.example.library.library_api.service;

import com.example.library.library_api.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
    Author findById(Long id);
    Author create(Author author);
    Author update(Long id,Author author);
    void delete(Long id);

}
