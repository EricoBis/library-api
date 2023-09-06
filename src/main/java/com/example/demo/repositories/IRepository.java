package com.example.demo.repositories;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    void add(T t);
    void remove(int id);
    void update(int id, T t);
}
