package com.example.pc.bookmanagerapplication;

import android.app.Application;

import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.repository.BookRepository;
import com.example.pc.bookmanagerapplication.repository.base.Repository;

public class BookManagerApp extends Application {

    private static Repository<Book> bookRecommendationsCollection;
    private static Repository<Book> wantToReadBookCollection;
    private static Repository<Book> readBookCollection;

    public static Repository<Book> getBookRepository(String collectionName){

        switch (collectionName) {

            case StringConstants.RECOMMENDATIONS:

                if (bookRecommendationsCollection == null) {
                    bookRecommendationsCollection = new BookRepository<>(Book.class, collectionName);
                }
                return bookRecommendationsCollection;

            case StringConstants.WANT_TO_READ:

                if (wantToReadBookCollection == null) {
                    wantToReadBookCollection = new BookRepository<>(Book.class, collectionName);
                }
                return wantToReadBookCollection;

                case StringConstants.READ_LIST:
                    if (readBookCollection == null) {
                        readBookCollection = new BookRepository<>(Book.class, collectionName);
                    }
                    return readBookCollection;
                    default:
                        return null;
        }
    }
}
