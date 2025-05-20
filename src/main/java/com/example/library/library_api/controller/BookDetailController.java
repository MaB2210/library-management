package com.example.library.library_api.controller;

import com.example.library.library_api.model.Book;
import com.example.library.library_api.model.BookDetail;
import com.example.library.library_api.service.BookDetailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/details")
public class BookDetailController {
    private final BookDetailService detailService;

    public BookDetailController(BookDetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping
    public List<BookDetail> getAll() {
        return detailService.findAll();
    }

    @GetMapping("/{id}")
    public BookDetail getOne(@PathVariable Long id) {
        return detailService.findById(id);
    }

    @PostMapping
    public ResponseEntity<BookDetail> create(@Valid @RequestBody BookDetail detail,
                                             @RequestParam Long bookId) {
        BookDetail created = detailService.create(detail, bookId);
        URI location = URI.create("/api/details/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public BookDetail update(@PathVariable Long id,
                             @Valid @RequestBody BookDetail detail) {
        return detailService.update(detail, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        detailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
