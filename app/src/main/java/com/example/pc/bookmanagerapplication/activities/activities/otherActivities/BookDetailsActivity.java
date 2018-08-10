package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.base.BaseDrawerActivity;
import com.example.pc.bookmanagerapplication.activities.fragments.BookOutlookFragment;
import com.example.pc.bookmanagerapplication.activities.fragments.DrawerFragment;
import com.example.pc.bookmanagerapplication.activities.fragments.ReplacingButtonFragment;
import com.example.pc.bookmanagerapplication.activities.models.Book;
//TODO add image, change layout, add "want to read" button, and "read" button, add infromation fragment
public class BookDetailsActivity extends AppCompatActivity {

    private BookOutlookFragment mBookOutlook;
    private DrawerFragment mDrawer;
    private ReplacingButtonFragment mReplacingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent fromRecommendationList = getIntent();
        Book book = (Book)fromRecommendationList.getSerializableExtra("BOOK");

        mBookOutlook = BookOutlookFragment.newInstance();
        mBookOutlook.setBook(book);

        mReplacingButton = ReplacingButtonFragment.newInstance();
        mReplacingButton.setCurrentBook(book);

        mDrawer = DrawerFragment.newInstance();
        mDrawer.setID(-1);


        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.book_outlook, mBookOutlook)
                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.replacing_button, mReplacingButton)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }
}
