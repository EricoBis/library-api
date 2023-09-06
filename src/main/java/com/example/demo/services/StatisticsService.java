package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.repositories.IRepository;

@Service
public class StatisticsService {
    
    private IRepository<Book> repository;

    @Autowired
    public StatisticsService(IRepository<Book> repository){
        this.repository = repository;
    }

    public long countBooks(String author) {
        return repository.getAll().stream()
                        .filter(book -> book.getAuthor().equals(author))
                        .count();
    }
}
