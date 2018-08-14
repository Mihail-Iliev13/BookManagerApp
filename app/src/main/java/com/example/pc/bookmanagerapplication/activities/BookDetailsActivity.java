package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.BookManagerApp;
import com.example.pc.bookmanagerapplication.activities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.fragments.BookOutlookFragment;
import com.example.pc.bookmanagerapplication.activities.fragments.DrawerFragment;
import com.example.pc.bookmanagerapplication.activities.fragments.ReplacingButtonFragment;
import com.example.pc.bookmanagerapplication.activities.models.Book;
//TODO add image, change layout, add "want to read" button, and "read" button, add infromation fragment
public class BookDetailsActivity extends AppCompatActivity {

    private BookOutlookFragment mBookOutlook;
    private DrawerFragment mDrawer;
    private ReplacingButtonFragment mReplacingButton;
    private String mCollectionName;
    private Book mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent fromRecommendationList = getIntent();
        mBook = (Book)fromRecommendationList.getSerializableExtra("BOOK");
        mCollectionName = fromRecommendationList.getStringExtra("COLLECTION_NAME");

        if (mCollectionName.equals(StringConstants.COLLECTION_READ)) {
            setContentView(R.layout.activity_one_button_book_details);

            mBookOutlook = BookOutlookFragment.newInstance();
            mBookOutlook.setBook(mBook);

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


        } else {
            mBookOutlook = BookOutlookFragment.newInstance();
            mBookOutlook.setBook(mBook);

            mReplacingButton = ReplacingButtonFragment.newInstance();
            mReplacingButton.setCurrentBook(mBook);
            mReplacingButton.setBookCollection(mCollectionName);


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
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }

    public void removeFromReadList(View view) {
        BookManagerApp.getBookRepository(StringConstants.COLLECTION_READ).remove(mBook);
        BookManagerApp.getBookRepository(StringConstants.COLLECTION_RECOMMENDATIONS).add(mBook);
        Button button = view.findViewById(R.id.btn_remove);
        button.setVisibility(View.INVISIBLE);
    }
}
