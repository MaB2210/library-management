package com.example.library.library_api.service;

import com.example.library.library_api.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    Book create(Book book,Long authorId);
    Book update(Long id,Book book);
    void delete(Long id);
    List<Book> findByAuthor(Long authorId);
}
