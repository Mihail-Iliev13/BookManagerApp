package com.example.pc.bookmanagerapplication.activities.repository;

import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;
import com.google.firebase.firestore.FirebaseFirestore;

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
    public void add(T book) {
        mFirebaseRepo.collection(mCollectionName)
                .add(book);
    }
}
