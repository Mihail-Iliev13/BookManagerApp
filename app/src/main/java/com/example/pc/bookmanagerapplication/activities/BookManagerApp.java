package com.example.pc.bookmanagerapplication.activities;

import android.app.Application;

import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.BookRepository;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;

import java.util.HashSet;

public class BookManagerApp extends Application {

    private static Repository<Book> bookRecommendationsRepository;
    private static Repository<Book> wantToReadBookRepository;
    private static Repository<Book> readBookRepository;

    public static Repository<Book> getBookRepository(String collectionName){

        switch (collectionName) {

            case StringConstants.COLLECTION_RECOMMENDATIONS:

                if (bookRecommendationsRepository == null) {
                    bookRecommendationsRepository = new BookRepository<>(Book.class, collectionName);
                }
                return bookRecommendationsRepository;

            case StringConstants.COLLECTION_WANT_TO_READ:

                if (wantToReadBookRepository == null) {
                    wantToReadBookRepository = new BookRepository<>(Book.class, collectionName);
                }
                return wantToReadBookRepository;

                case StringConstants.COLLECTION_READ:
                    if (readBookRepository == null) {
                        readBookRepository = new BookRepository<>(Book.class, collectionName);
                    }
                    return readBookRepository;
                    default:
                        return null;
        }
    }


}
