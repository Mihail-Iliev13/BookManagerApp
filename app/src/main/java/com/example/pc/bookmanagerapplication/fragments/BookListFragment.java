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
    private Book mClickedBook;
    private boolean mAlreadyStarted;

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
        mAlreadyStarted = false;

        mBookList = mView.findViewById(R.id.lv_book_list);

        mBookList.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent toBookDetails =
                    new Intent(getContext(), BookDetailsActivity.class);

            mClickedBook= (Book) mAdapter.getItem(i);
            toBookDetails.putExtra(StringConstants.BOOK, mClickedBook);
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

        if (mAlreadyStarted) {
            mAdapter.remove(mClickedBook);
            mAdapter.notifyDataSetChanged();
            return;
        }

        mAlreadyStarted = true;

        mAdapter = new CustomArrayAdapter(getContext(), R.layout.custom_list_view, mBooks);
        FirebaseFirestore mDb = FirebaseFirestore.getInstance();

        if (mBookCollection.getCollectionName()
                .equals(StringConstants.RECOMMENDATIONS)) {

            mDb.collection(StringConstants.RECOMMENDATIONS).addSnapshotListener((queryDocumentSnapshots, e) -> {

                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                    if (doc.getType() != DocumentChange.Type.ADDED) {

                        continue;

                    }

                        Book book = doc.getDocument().toObject(Book.class);

                        if (!RecommendationsListActivity
                                .mSelectedGenres
                                .contains(book.genre)) {

                            continue;
                        }

                        mBooks.add(book);
                        mAdapter.notifyDataSetChanged();

                }
            });

        } else {
            mDb.collection(mBookCollection.getCollectionName()).addSnapshotListener((queryDocumentSnapshots, e) -> {

                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                    if (doc.getType() != DocumentChange.Type.ADDED) {
                        continue;
                    }

                        Book book = doc.getDocument().toObject(Book.class);


                        mBooks.add(book);

                        mAdapter.notifyDataSetChanged();

                }
            });
        }


        mBookList.setAdapter(mAdapter);

    }
}
