package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.BookManagerApp;
import com.example.pc.bookmanagerapplication.activities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.fragments.DrawerFragment;
import com.example.pc.bookmanagerapplication.activities.fragments.BookListFragment;
import com.example.pc.bookmanagerapplication.activities.models.Book;

import java.util.HashSet;


//TODO RecommendationList to fragment
public class RecommendationsListActivity extends AppCompatActivity {

    ArrayAdapter mAdapter;
    public static HashSet<String> mSelectedOptions;
    BookListFragment mListFragment;
    DrawerFragment mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations_list);
        Intent intent = getIntent();
        mSelectedOptions = (HashSet<String>) intent.getSerializableExtra("SELECTED_GENRES");
        mListFragment = BookListFragment.newInstance();
        generateBookListContent();


        mDrawer = DrawerFragment.newInstance();
        mDrawer.setID(-1);
        setSupportActionBar(mDrawer.getToolbar());

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.lv_content, mListFragment)
                .commit();

    }

    private void generateBookListContent() {
        mAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1);
        BookManagerApp.getBookRepository(StringConstants.COLLECTION_RECOMMENDATIONS).getAll(books -> {

            for (Book book : books) {

                if (mSelectedOptions.contains(book.genre)) {
                        mAdapter.add(book);

                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
        mListFragment.setBookCollection(StringConstants.COLLECTION_RECOMMENDATIONS);
    }
}
