package com.example.pc.bookmanagerapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.fragments.BookListFragment;
import com.example.pc.bookmanagerapplication.fragments.DrawerFragment;

public class ReadBooksActivity extends AppCompatActivity {

    public static final long ID = 3;
    DrawerFragment mDrawer;
    BookListFragment mListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_books);

        mDrawer = DrawerFragment.newInstance();
        mDrawer.setID(ID);
        setSupportActionBar(mDrawer.getToolbar());

        mListFragment = BookListFragment.newInstance();


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
        mListFragment.setBookCollection(StringConstants.READ_LIST);
    }
}
