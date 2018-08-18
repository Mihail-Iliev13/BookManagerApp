package com.example.pc.bookmanagerapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pc.bookmanagerapplication.BookManagerApp;
import com.example.pc.bookmanagerapplication.CustomArrayAdapter;
import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.activities.RecommendationsListActivity;
import com.example.pc.bookmanagerapplication.activities.BookDetailsActivity;
import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.repository.base.Repository;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class BookListFragment extends Fragment {

    private CustomArrayAdapter mAdapter;
    private Repository<Book> mBookCollection;
    private ListView mBookList;
    private List<Book> mBooks;

    public BookListFragment() {

    }

    public static BookListFragment newInstance(){
        return new BookListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView =  inflater.inflate(R.layout.fragment_book_list, container, false);

        mBooks = new ArrayList<>();
        mAdapter = new CustomArrayAdapter(getContext(), R.layout.custom_list_view, mBooks);

        FirebaseFirestore mDb = FirebaseFirestore.getInstance();
        mDb.collection(StringConstants.WANT_TO_READ).addSnapshotListener((queryDocumentSnapshots, e) -> {

            for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                if (doc.getType() == DocumentChange.Type.ADDED) {

                    Book book = doc.getDocument().toObject(Book.class);
                    mBooks.add(book);

                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        mBookList = mView.findViewById(R.id.lv_book_list);

        mBookList.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent toBookDetails =
                    new Intent(getContext(), BookDetailsActivity.class);

            Book book = (Book) mAdapter.getItem(i);
            toBookDetails.putExtra(StringConstants.BOOK, book);
            toBookDetails.putExtra(StringConstants.COLLECTION_NAME, mBookCollection.getCollectionName());
            startActivity(toBookDetails);
        });

        return mView;
    }


    public void setBookCollection(String collectionName) {
        this.mBookCollection = BookManagerApp.getBookRepository(collectionName);
    }


    @Override
    public void onStart() {
        super.onStart();

        if (mBookCollection.getCollectionName()
                .equals(StringConstants.RECOMMENDATIONS)) {

            mBookCollection.getAll(books -> {

                for (Book book : books) {
                    if (RecommendationsListActivity
                            .mSelectedGenres
                            .contains(book.genre)) {

                        mAdapter.add(book);
                    }
                }
            });

        } else {
            mBookCollection.getAll(books -> {

                for (Book book : books) {
                  mAdapter.add(book);
                }
            });
        }

        mBookList.setAdapter(mAdapter);

    }
}
