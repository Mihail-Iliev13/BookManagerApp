package com.example.pc.bookmanagerapplication;

import android.app.Application;

import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.repository.BookRepository;
import com.example.pc.bookmanagerapplication.repository.base.Repository;
import com.example.pc.bookmanagerapplication.utillities.StringConstants;

public class BookManagerApp extends Application {

    private static Repository<Book> bookRecommendationsCollection;
    private static Repository<Book> wantToReadBookCollection;
    private static Repository<Book> readBookCollection;

    public static Repository<Book> getWantToReadCollection(){

        if (wantToReadBookCollection == null) {
            wantToReadBookCollection = new BookRepository<>(Book.class, StringConstants.WANT_TO_READ);
        }
        return wantToReadBookCollection;
    }

    public static Repository<Book> getReadBooksCollection() {
        if (readBookCollection == null) {
            readBookCollection = new BookRepository<>(Book.class, StringConstants.READ_BOOKS);
        }
        return readBookCollection;
    }

    public static Repository<Book> getBookRecommendationsCollection () {

        if (bookRecommendationsCollection == null) {
            bookRecommendationsCollection = new BookRepository<>(Book.class, StringConstants.RECOMMENDATIONS);
        }
        return bookRecommendationsCollection;
    }
}
