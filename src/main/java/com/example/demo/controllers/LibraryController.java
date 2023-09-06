package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.BookIdAlreadyExistsException;
import com.example.demo.exceptions.BookIdNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.services.LibraryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/library")
public class LibraryController {

    LibraryService service;

    public LibraryController(LibraryService service) {
        this.service = service;
    }

    // GET

    @GetMapping("/books")
    public List<Book> getBookList() {
        return service.findAllBooks();
    }

    @GetMapping("/books/{author}/{year}")
    public List<Book> getAuthorBooksByYear(@PathVariable(value = "author") String author,
            @PathVariable(value = "year") int year) {
        return service.findAllAuthorBooksByYear(year);
    }

    @GetMapping("/booksyear")
    public List<Book> getBooksByYear(@RequestParam(value = "year") String year) {
        return service.findAllBooksByYear(year);
    }

    @GetMapping("/authorbooks")
    public List<Book> getAuthorBooks(@RequestParam(value = "author") String author) {
        return service.findAllAuthorBooks(author);
    }

    @GetMapping("/outdated/{year}")
    public List<Book> getOutdatedBooks(@PathVariable(value = "year") int year) {
        return service.findOutdatedByYear(year);
    }

    @GetMapping("/titles")
    public List<String> getTitlesList() {
        return service.findAllTitles();
    }

    @GetMapping("/authors")
    public List<String> getAuthorsList() {
        return service.findAllAuthors();
    }

    // POST

    @PostMapping("/register")
    public ResponseEntity<Book> addNewBook(@RequestBody final Book book) {
        Book resp = service.registerBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // PUT

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") int id, @RequestBody Book book) {
        Book resp = service.updateBook(id, book);
        return ResponseEntity.ok(resp);
    }

    // DELETE

    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteBook(@RequestParam(value = "id") int id) {
        service.removeBookById(id);
        return ResponseEntity.ok("Book Removed");
    }

    // EXCEPTION

    @ExceptionHandler({ BookIdAlreadyExistsException.class, BookIdNotFoundException.class })
    public ResponseEntity<String> handleLivroIdExistenteException(RuntimeException error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
}
