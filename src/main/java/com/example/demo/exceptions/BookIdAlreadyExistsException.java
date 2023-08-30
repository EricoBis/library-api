package com.example.demo.exceptions;

public class BookIdAlreadyExistsException extends RuntimeException {

    public BookIdAlreadyExistsException(int id){
        super("The ID " + id + " already exists.");
    }
}