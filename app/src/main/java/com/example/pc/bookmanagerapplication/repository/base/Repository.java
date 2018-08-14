package com.example.pc.bookmanagerapplication.repository.base;

import android.content.Context;

import com.example.pc.bookmanagerapplication.activities.models.Book;

import java.util.List;
import java.util.function.Consumer;

public interface Repository<T> {

    void getAll(Consumer<List<T>> action);

    void add (Book book);

//    boolean contains(Book book);

    void remove (Book book);

    String getCollectionName();
}
