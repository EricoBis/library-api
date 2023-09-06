package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.services.StatisticsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/statistics")
public class StatisticsController {

    StatisticsService service;

    @Autowired
    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    // GET

    @GetMapping("/booksquantity")
    public long getAuthorBooksQuantity(@RequestParam(value = "author") String author) {
       return service.countBooks(author);
    }

    @GetMapping("/booksquantityfrom")
    public long getBooksQuantityFrom(@RequestParam(value = "year") int year) {
       return service.countBooksFrom(year);
    }

    // @GetMapping("/averagebooks")
    // public long getAverageBooksFromAuthor(@RequestParam(value = "year") int year) {
    //    return service.countBooksFrom(year);
    // }

    // POST

    // DELETE
}
