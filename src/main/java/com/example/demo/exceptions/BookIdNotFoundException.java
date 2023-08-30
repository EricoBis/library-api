package com.example.demo.exceptions;

public class BookIdNotFoundException extends RuntimeException{
    
    public BookIdNotFoundException(int id){
        super("The ID " + id + " was not found.");
    }
}
