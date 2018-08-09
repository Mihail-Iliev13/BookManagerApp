package com.example.pc.bookmanagerapplication.activities;

import android.app.Application;

import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.BookRepository;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;

public class BookManagerApp extends Application {

    public static Repository<Book> bookRepository;

    public static Repository<Book> getBookRepository(String collectionName){

        if (bookRepository == null) {
            bookRepository = new BookRepository<>(Book.class, collectionName);
        }

        return bookRepository;
    }


}
