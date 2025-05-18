package com.example.library.library_api.service;

import com.example.library.library_api.exception.ResourceNotFoundException;
import com.example.library.library_api.model.Author;
import com.example.library.library_api.model.Book;
import com.example.library.library_api.repository.AuthorRepository;
import com.example.library.library_api.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book","id",id));
    }

    @Override
    @Transactional
    public Book create(Book book, Long  authorId) {
        Author existing = authorService.findById(authorId);
        book.setAuthor(existing);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book update(Long id, Book book) {
        Book existing = findById(id);
        existing.setDetail(book.getDetail());
        existing.setTitle(book.getTitle());
        existing.setPublishedDate(book.getPublishedDate());
        return bookRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book existing = findById(id);
        bookRepository.delete(existing);
    }

    @Override
    public List<Book> findByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
