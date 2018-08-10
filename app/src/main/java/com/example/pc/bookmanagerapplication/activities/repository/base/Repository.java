package com.example.pc.bookmanagerapplication.activities.repository.base;

import android.content.Context;

import com.example.pc.bookmanagerapplication.activities.models.Book;

import java.util.List;
import java.util.function.Consumer;

public interface Repository<T> {

    void getAll(Consumer<List<T>> action);

    void add (Book book);

    boolean contains(T book);

    void remove (Book book);
}
