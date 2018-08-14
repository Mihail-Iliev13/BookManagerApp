package com.example.pc.bookmanagerapplication;

import android.app.Application;

import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.repository.BookRepository;
import com.example.pc.bookmanagerapplication.repository.base.Repository;

public class BookManagerApp extends Application {

    private static Repository<Book> bookRecommendationsRepository;
    private static Repository<Book> wantToReadBookRepository;
    private static Repository<Book> readBookRepository;

    public static Repository<Book> getBookRepository(String collectionName){

        switch (collectionName) {

            case StringConstants.RECOMMENDATIONS:

                if (bookRecommendationsRepository == null) {
                    bookRecommendationsRepository = new BookRepository<>(Book.class, collectionName);
                }
                return bookRecommendationsRepository;

            case StringConstants.WANT_TO_READ:

                if (wantToReadBookRepository == null) {
                    wantToReadBookRepository = new BookRepository<>(Book.class, collectionName);
                }
                return wantToReadBookRepository;

                case StringConstants.READ_LIST:
                    if (readBookRepository == null) {
                        readBookRepository = new BookRepository<>(Book.class, collectionName);
                    }
                    return readBookRepository;
                    default:
                        return null;
        }
    }


}
