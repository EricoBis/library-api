package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Book;

@Repository
public class LibraryRepository implements IRepository<Book> {

    private List<Book> books;

    public LibraryRepository() {
        books = new ArrayList<>();

        books.add(new Book(1, "Percy Jackson e o Ladrão de Raios", "Rick Riordan", 2005));
        books.add(new Book(2, "Jogos Vorazes", "Suzanne Collins", 2008));
        books.add(new Book(3, "O Mar de Monstros", "Rick Riordan", 2006));
        books.add(new Book(4, "Em Chamas", "Suzanne Collins", 2009));
        books.add(new Book(5, "A Maldição do Titã", "Rick Riordan", 2007));
        books.add(new Book(6, "A Esperança", "Suzanne Collins", 2010));
        books.add(new Book(7, "A Torre Negra", "Stephen King", 1982));
        books.add(new Book(8, "As Crônicas de Gelo e Fogo: A Guerra dos Tronos", "George R. R. Martin", 1996));
        books.add(new Book(9, "Duna", "Frank Herbert", 1965));
        books.add(new Book(10, "O Nome do Vento", "Patrick Rothfuss", 2007));
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public void remove(int id) {
        books.removeIf(book -> book.getId() == id);
    }

}
