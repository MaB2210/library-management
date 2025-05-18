package com.example.library.library_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private int pageCount;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "book_id",nullable = false,unique = true)
    private Book book;

}
