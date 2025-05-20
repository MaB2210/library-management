package com.example.library.library_api.service;

import com.example.library.library_api.exception.ResourceNotFoundException;
import com.example.library.library_api.model.Author;
import com.example.library.library_api.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author","id",id));
    }

    @Override
    @Transactional
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author update(Long id, Author author) {
        Author existing = findById(id);
        existing.setName(author.getName());
        existing.setBio(author.getBio());
        return authorRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Author existing = findById(id);
        authorRepository.delete(existing);
    }
}
