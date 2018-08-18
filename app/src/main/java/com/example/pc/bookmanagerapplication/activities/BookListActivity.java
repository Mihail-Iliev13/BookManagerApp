package com.example.pc.bookmanagerapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.fragments.BookListFragment;
import com.example.pc.bookmanagerapplication.fragments.DrawerFragment;

public class BookListActivity extends AppCompatActivity {

    private DrawerFragment mDrawer;
    private BookListFragment mBookListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mDrawer = DrawerFragment.newInstance();

        mDrawer.setID(getIntent()
                .getLongExtra(StringConstants.CURRENT_ID, 0));

        setSupportActionBar(mDrawer.getToolbar());

        mBookListFragment = BookListFragment.newInstance();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.lv_book_list, mBookListFragment)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
        Intent fromDrawer = getIntent();
        mBookListFragment.setBookCollection(
                fromDrawer.getStringExtra(StringConstants.COLLECTION_NAME));
    }
}
