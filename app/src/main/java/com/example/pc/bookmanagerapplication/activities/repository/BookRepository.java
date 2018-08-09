package com.example.pc.bookmanagerapplication.activities.repository;

import android.support.annotation.NonNull;

import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.function.Consumer;

public class BookRepository<T> {

    private final String mCollectionName;
    private FirebaseFirestore mFirebaseFirestore;

    public BookRepository(String collectionName) {
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        mCollectionName = collectionName;
    }

    public void getAll (Consumer<List<Book>> action){
        mFirebaseFirestore.collection(mCollectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Book> books = task.getResult().toObjects(Book.class);
                        action.accept(books);
                    }
                });
    }

    public void add (Book book) {
        mFirebaseFirestore.collection(mCollectionName)
                .add(book);
    }

}
