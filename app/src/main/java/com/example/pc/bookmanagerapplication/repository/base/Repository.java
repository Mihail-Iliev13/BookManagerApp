package com.example.pc.bookmanagerapplication.repository.base;

import android.widget.ArrayAdapter;

import com.example.pc.bookmanagerapplication.CustomArrayAdapter;
import com.example.pc.bookmanagerapplication.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface Repository<T> {

    void toAdapter(List<Book> bookList, CustomArrayAdapter adapter);

    void add (Book book);

    void remove (Book book);


    String getCollectionName();

}
