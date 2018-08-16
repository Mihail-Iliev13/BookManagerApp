package com.example.pc.bookmanagerapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.fragments.BookListFragment;
import com.example.pc.bookmanagerapplication.fragments.DrawerFragment;

public class ReadBooksActivity extends AppCompatActivity {

    public static final long ID = 3;
    private DrawerFragment mDrawer;
    private BookListFragment mBookListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_books);

        mDrawer = DrawerFragment.newInstance();
        mDrawer.setID(ID);
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
        mBookListFragment.setBookCollection(StringConstants.READ_LIST);
    }
}
