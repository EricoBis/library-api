package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.BookIdAlreadyExistsException;
import com.example.demo.exceptions.BookIdNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.repositories.IRepository;

@Service
public class LibraryService {

    private IRepository<Book> repository;

    @Autowired
    public LibraryService(IRepository<Book> repository) {
        this.repository = repository;
    }

    public List<Book> findAllBooks() {
        return repository.getAll();
    }

    public List<String> findAllTitles() {
        return repository.getAll().stream()
                .map(book -> book.getTitle())
                .toList();
    }

    public List<String> findAllAuthors() {
        return repository.getAll().stream()
                .map(book -> book.getAuthor())
                .distinct()
                .toList();
    }

    public boolean idExists(int id) {
        return repository.getAll().stream()
                .anyMatch(book -> book.getId() == id);
    }

    public Book registerBook(Book book) {
        int id = book.getId();
        if (idExists(id)) {
            throw new BookIdAlreadyExistsException(id);
        }
        repository.add(book);
        return book;
    }

    public void removeBookById(int id) {
        if (!idExists(id)) {
            throw new BookIdNotFoundException(id);
        }
        repository.remove(id);
    }

    public List<Book> findAllAuthorBooks(String author) {
        return repository.getAll().stream()
                .filter(book -> book.getAuthor().equals(author))
                .toList();
    }

    public List<Book> findAllBooksByYear(String year) {
        return repository.getAll().stream()
                .filter(book -> String.valueOf(book.getYear()).equals(year))
                .toList();
    }

    public List<Book> findOutdatedByYear(int year) {
        return repository.getAll().stream()
                .filter(book -> book.getYear() < year)
                .toList();
    }

    public List<Book> findAllAuthorBooksByYear(int year) {
        return repository.getAll().stream()
                        .filter(book -> book.getYear() == year)
                        .toList();
    }

    public Book updateBook(int id, Book book) {
        if (!idExists(id)) {
            throw new BookIdNotFoundException(id);
        }
        repository.update(id, book);
        return book;
    }
}
