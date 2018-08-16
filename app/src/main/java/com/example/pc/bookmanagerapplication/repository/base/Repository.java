package com.example.pc.bookmanagerapplication.repository.base;

import com.example.pc.bookmanagerapplication.models.Book;

import java.util.List;
import java.util.function.Consumer;

public interface Repository<T> {

    void getAll(Consumer<List<T>> action);

    void add (Book book);

    void remove (Book book);

    String getCollectionName();
}
