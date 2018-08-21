package com.example.pc.bookmanagerapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pc.bookmanagerapplication.BookManagerApp;
import com.example.pc.bookmanagerapplication.utillities.CustomArrayAdapter;
import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.utillities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.BookDetailsActivity;
import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.repository.base.Repository;
import com.example.pc.bookmanagerapplication.utillities.ToastShower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BookListFragment extends Fragment implements Serializable{

    private CustomArrayAdapter mAdapter;
    private Repository<Book> mBookCollection;
    private ListView mBookListView;
    private List<Book> mBooks;
    private Book mClickedBook;
    private boolean mAlreadyCreated;
    private static boolean mShouldRemoveBookFromList;

    public BookListFragment() {

    }

    public static BookListFragment newInstance(){
        return new BookListFragment();
    }

    public static void setShouldRemoveBookFromList(boolean shouldRemove) {
        mShouldRemoveBookFromList = shouldRemove;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView =  inflater.inflate(R.layout.fragment_book_list, container, false);
        mBooks = new ArrayList<>();
        mAdapter = new CustomArrayAdapter(getContext(), R.layout.custom_list_view, mBooks);

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

            ToastShower.showBookGenre((Book)mAdapter.getItem(position), getContext());

            return true;
        });

        return mView;
    }


    public void setBookCollection(String collectionName) {
        switch (collectionName) {
            case StringConstants.RECOMMENDATIONS:
                mBookCollection = BookManagerApp.getBookRecommendationsCollection();
                return;
            case StringConstants.READ_LIST:
                mBookCollection = BookManagerApp.getReadBooksCollection();
                return;
            case StringConstants.WANT_TO_READ:
                mBookCollection = BookManagerApp.getWantToReadCollection();
                return;
            default:
                return;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mAlreadyCreated) {

            if (mShouldRemoveBookFromList) {

                mAdapter.remove(mClickedBook);
                mAdapter.notifyDataSetChanged();
            }

            return;
        }

        mAlreadyCreated = true;
        mBookCollection.collectionToAdapter(mBooks, mAdapter);
        mBookListView.setAdapter(mAdapter);

    }
}
