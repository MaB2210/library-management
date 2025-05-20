package com.example.library.library_api.service;

import com.example.library.library_api.exception.ResourceNotFoundException;
import com.example.library.library_api.model.Book;
import com.example.library.library_api.model.BookDetail;
import com.example.library.library_api.repository.BookDetailRepository;
import com.example.library.library_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookDetailServiceImpl implements BookDetailService {
    private final BookDetailRepository bookDetailRepository;
    private final BookRepository bookRepository;

    public BookDetailServiceImpl(BookDetailRepository bookDetailRepository,
                                 BookRepository bookRepository) {
        this.bookDetailRepository = bookDetailRepository;
        this.bookRepository   = bookRepository;
    }

    @Override
    public List<BookDetail> findAll() {
        return bookDetailRepository.findAll();
    }

    @Override
    public BookDetail findById(Long id) {
        return bookDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BookDetail","id",id));
    }

    @Override
    @Transactional
    public BookDetail create(BookDetail bookDetail, Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("book","id",bookId));
        bookDetail.setBook(book);
        return bookDetailRepository.save(bookDetail);
    }

    @Override
    @Transactional
    public BookDetail update(BookDetail bookDetail, Long id) {
        BookDetail existing = findById(id);
        existing.setIsbn(bookDetail.getIsbn());
        existing.setPageCount(bookDetail.getPageCount());
        return bookDetailRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        BookDetail bookDetail = findById(id);
        bookDetailRepository.delete(bookDetail);
    }
}
