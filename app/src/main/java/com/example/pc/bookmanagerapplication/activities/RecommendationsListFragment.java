package com.example.pc.bookmanagerapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.otherActivities.BookDetailsActivity;
import com.example.pc.bookmanagerapplication.activities.activities.otherActivities.RecommendationsListActivity;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;

import java.util.HashSet;

public class RecommendationsListFragment extends Fragment {

    ArrayAdapter<Book> mAdatper;
    Repository<Book> mBookRepository;
    private ListView mRecommendations;
    private HashSet<String> mSelectedOptions;

    public RecommendationsListFragment() {

    }

    public static RecommendationsListFragment newInstance(){
        return new RecommendationsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView =  inflater.inflate(R.layout.fragment_recommendation_list, container, false);
        mBookRepository = BookManagerApp.getBookRepository(StringConstants.COLLECTION_RECOMMENDATIONS);
        mAdatper = new ArrayAdapter<Book>(getContext(), android.R.layout.simple_list_item_1);
        generateBookDetailsListContent();

        mRecommendations = mView.findViewById(R.id.lv_recommendations);
        mRecommendations.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent toBookDetails =
                    new Intent(getContext(), BookDetailsActivity.class);

            Book book = mAdatper.getItem(i);
            toBookDetails.putExtra("BOOK", book);
            startActivity(toBookDetails);
        });
        mRecommendations.setAdapter(mAdatper);
        return mView;
    }

    private void generateBookDetailsListContent() {

        mBookRepository.getAll(books -> {

            for (Book book : books) {

                if (mSelectedOptions.contains(book.genre)) {
                    mAdatper.add(book);
                }
            }
        });
    }

    public void setSelectedOptions (HashSet<String> selectedOptions) {
        mSelectedOptions = selectedOptions;
    }

}
