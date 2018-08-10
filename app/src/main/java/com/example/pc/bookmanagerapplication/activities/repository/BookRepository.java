package com.example.pc.bookmanagerapplication.activities.repository;

import android.support.annotation.NonNull;

import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BookRepository<T> implements Repository<T> {

    private final FirebaseFirestore mFirebaseRepo;
    private final Class<T> mKlass;
    private final String mCollectionName;

    public BookRepository (Class<T> klass, String collectionName) {
        mFirebaseRepo = FirebaseFirestore.getInstance();
        mKlass = klass;
        mCollectionName = collectionName;
    }


   public List<Book> books = new ArrayList<>();


    @Override
    public boolean contains(T book) {

        final boolean[] contains = {false};
        mFirebaseRepo.collection(mCollectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        contains[0] =  task.getResult().toObjects(mKlass).contains(book);
                    }
                });

        return contains[0];
    }

    @Override
    public void remove (Book book) {

        mFirebaseRepo.collection(mCollectionName)
                .document(book.docRef)
                .delete();
    }

    @Override
    public void getAll(Consumer<List<T>> action) {

        mFirebaseRepo.collection(mCollectionName)
                .get()
                .addOnCompleteListener(task -> {
                   List<T> books = task.getResult()
                           .toObjects(mKlass);
                   action.accept(books);
                });
    }

    @Override
    public void add(Book book) {
        DocumentReference docRef = FirebaseFirestore
                .getInstance()
                .collection(mCollectionName)
                .document(book.docRef);

        docRef.set(book);
    }
}
