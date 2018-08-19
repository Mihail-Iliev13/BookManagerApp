package com.example.pc.bookmanagerapplication.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.utillities.StringConstants;
import com.example.pc.bookmanagerapplication.fragments.DrawerFragment;
import com.example.pc.bookmanagerapplication.fragments.BookListFragment;

import java.util.HashSet;


public class RecommendationsListActivity extends AppCompatActivity {

    public static HashSet<String> mSelectedGenres;
    private DrawerFragment mDrawer;
    private BookListFragment mListFragment;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mProgressDialog = new ProgressDialog(this);



        Intent intent = getIntent();
        mSelectedGenres = (HashSet<String>) intent.getSerializableExtra(StringConstants.SELECTED_GENRES);
        mListFragment = BookListFragment.newInstance();

        mDrawer = DrawerFragment.newInstance();
        mDrawer.setID(-1);
        setSupportActionBar(mDrawer.getToolbar());

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.lv_book_list, mListFragment)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mDrawer.setupDrawer();
        mListFragment.setBookCollection(StringConstants.RECOMMENDATIONS);
    }

}
