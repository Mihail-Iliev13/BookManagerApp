package com.example.pc.bookmanagerapplication.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.BookManagerApp;
import com.example.pc.bookmanagerapplication.activities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.activities.otherActivities.BookDetailsActivity;
import com.example.pc.bookmanagerapplication.activities.activities.otherActivities.RecommendationsListActivity;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;


public class BookListFragment extends Fragment {

    ArrayAdapter<Book> mAdatper;
    Repository<Book> mBookCollection;
    private ListView mBookList;

    public BookListFragment() {

    }

    public static BookListFragment newInstance(){
        return new BookListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView =  inflater.inflate(R.layout.fragment_book_list, container, false);
        mAdatper = new ArrayAdapter<Book>(getContext(), android.R.layout.simple_list_item_1);

        mBookList = mView.findViewById(R.id.lv_book_list);


        mBookList.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent toBookDetails =
                    new Intent(getContext(), BookDetailsActivity.class);

            Book book = mAdatper.getItem(i);
            toBookDetails.putExtra("BOOK", book);
            toBookDetails.putExtra("COLLECTION_NAME", mBookCollection.getCollectionName());
            startActivity(toBookDetails);
        });

        return mView;
    }


    public void setBookCollection(String collectionName) {
        this.mBookCollection = BookManagerApp.getBookRepository(collectionName);
    }

    public void setListAdapter(ArrayAdapter adapter){
        mAdatper = adapter;
        mBookList.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mBookCollection.getCollectionName().equals(StringConstants.COLLECTION_RECOMMENDATIONS)) {
            mBookCollection.getAll(books -> {

                for (Book book : books) {
                    if (RecommendationsListActivity.mSelectedOptions.contains(book.genre)) {
                        mAdatper.add(book);
                    }
                }

            });

        } else {
            mBookCollection.getAll(books -> {

                for (Book book : books) {
                  mAdatper.add(book);
                }

            });
        }
        mBookList.setAdapter(mAdatper);
    }
}
