package com.example.pc.bookmanagerapplication.repository;


import android.support.annotation.NonNull;

import com.example.pc.bookmanagerapplication.CustomArrayAdapter;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.activities.RecommendationsListActivity;
import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.repository.base.Repository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class BookRepository<T> implements Repository<T> {

    private final FirebaseFirestore mFirebaseRepo;
    private final Class<T> mKlass;
    private final String mCollectionName;
    private List<Book> books;
    private Boolean contains;

    public BookRepository (Class<T> klass, String collectionName) {
        mFirebaseRepo = FirebaseFirestore.getInstance();
        mKlass = klass;
        mCollectionName = collectionName;
        books = new ArrayList<>();
    }


    @Override
    public void remove (Book book) {

        mFirebaseRepo.collection(mCollectionName)
                .document(book.docRef)
                .delete();
    }

    @Override
    public String getCollectionName() {
        return mCollectionName;
    }


    @Override
    public void toAdapter (List<Book> bookList, CustomArrayAdapter adapter) {

            mFirebaseRepo.collection(mCollectionName)
                    .addSnapshotListener((queryDocumentSnapshots, e) -> {

                        for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                            if (doc.getType() != DocumentChange.Type.ADDED) {
                                continue;
                            }

                            Book book = doc.getDocument().toObject(Book.class);

                            if (mCollectionName.equals(StringConstants.RECOMMENDATIONS)){
                                if (!RecommendationsListActivity
                                        .mSelectedGenres
                                        .contains(book.genre)) {
                                    continue;
                                }
                            }

                           bookList.add(book);
                           adapter.notifyDataSetChanged();
                        }
                    });
    }

    @Override
    public void add (Book book) {
        DocumentReference docRef = FirebaseFirestore
                .getInstance()
                .collection(mCollectionName)
                .document(book.docRef);

        docRef.set(book);
    }
}
