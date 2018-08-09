package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.BookManagerApp;
import com.example.pc.bookmanagerapplication.activities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;



public class RecommendationsListActivity extends AppCompatActivity {
    ListView mRecommendations;
    ArrayAdapter<Book> mAdatper;
    List<String> mBookDetails;
    HashSet<String> mSelectedOptions;
    Repository<Book> mBookRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations_list);
        Intent intent = getIntent();
        mSelectedOptions = (HashSet<String>) intent.getSerializableExtra("SELECTED_GENRES");
        mBookRepository = BookManagerApp.getBookRepository(StringConstants.COLLECTION_RECOMMENDATIONS);
        mAdatper = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1);
        generateBookDetailsListContent();

        mRecommendations = findViewById(R.id.lv_recommendations);
        mRecommendations.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent toBookDetails =
                    new Intent(RecommendationsListActivity.this, BookDetailsActivity.class);

                Book book = mAdatper.getItem(i);
                toBookDetails.putExtra("BOOK", book);
                startActivity(toBookDetails);
        });
        mRecommendations.setAdapter(mAdatper);
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
}
