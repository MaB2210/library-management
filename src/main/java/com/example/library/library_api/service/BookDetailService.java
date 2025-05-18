package com.example.library.library_api.service;

import com.example.library.library_api.model.Book;
import com.example.library.library_api.model.BookDetail;

import java.util.List;

public interface BookDetailService {
    List<BookDetail> findAll();
    BookDetail findById(Long id);
    BookDetail create(BookDetail bookDetail,Long bookId);
    BookDetail update(BookDetail bookDetail, Long bookId);
    void delete (Long id);
}
