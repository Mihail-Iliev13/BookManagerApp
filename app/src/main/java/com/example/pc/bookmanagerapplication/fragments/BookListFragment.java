package com.example.pc.bookmanagerapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.bookmanagerapplication.BookManagerApp;
import com.example.pc.bookmanagerapplication.CustomArrayAdapter;
import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.activities.BookDetailsActivity;
import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.repository.base.Repository;

import java.util.ArrayList;
import java.util.List;


public class BookListFragment extends Fragment {

    public static boolean shouldRemoveBookFromListView;
    private CustomArrayAdapter mAdapter;
    private Repository<Book> mBookCollection;
    private ListView mBookListView;
    private List<Book> mBooks;
    private Book mClickedBook;
    private boolean mAlreadyCreated;

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
        mAlreadyCreated = false;

        mBookListView = mView.findViewById(R.id.lv_book_list);

        mBookListView.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent toBookDetails =
                    new Intent(getContext(), BookDetailsActivity.class);

            mClickedBook = (Book) mAdapter.getItem(i);
            toBookDetails.putExtra(StringConstants.BOOK, mClickedBook);
            toBookDetails.putExtra(StringConstants.COLLECTION_NAME, mBookCollection.getCollectionName());
            startActivity(toBookDetails);
        });

        mBookListView.setOnItemLongClickListener((parent, view, position, id) -> {

            Toast.makeText(
                    getContext(),
                    "Genre: " + ((Book)mAdapter.getItem(position)).genre,
                    Toast.LENGTH_SHORT)
                    .show();

            return true;
        });

        return mView;
    }


    public void setBookCollection(String collectionName) {
        this.mBookCollection = BookManagerApp.getBookRepository(collectionName);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mAlreadyCreated) {

            if (shouldRemoveBookFromListView) {

                mAdapter.remove(mClickedBook);
                mAdapter.notifyDataSetChanged();
            }

            return;
        }

        mAlreadyCreated = true;
        mAdapter = new CustomArrayAdapter(getContext(), R.layout.custom_list_view, mBooks);
        mBookCollection.toAdapter(mBooks, mAdapter);
        mBookListView.setAdapter(mAdapter);

    }

}
