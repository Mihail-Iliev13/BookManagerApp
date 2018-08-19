package com.example.pc.bookmanagerapplication.repository.base;

import com.example.pc.bookmanagerapplication.utillities.CustomArrayAdapter;
import com.example.pc.bookmanagerapplication.models.Book;

import java.util.List;

public interface Repository<T> {

    void toAdapter(List<Book> bookList, CustomArrayAdapter adapter);

    void add (Book book);

    void remove (Book book);


    String getCollectionName();

}
