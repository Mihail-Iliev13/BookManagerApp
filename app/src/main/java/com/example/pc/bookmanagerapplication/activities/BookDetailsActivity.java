package com.example.pc.bookmanagerapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pc.bookmanagerapplication.BookManagerApp;
import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.BookManagerApp;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.fragments.BookOutlookFragment;
import com.example.pc.bookmanagerapplication.fragments.DrawerFragment;
import com.example.pc.bookmanagerapplication.fragments.ReplacingButtonFragment;

//TODO add image, change layout, add "want to read" button, and "read" button, add infromation fragment
public class BookDetailsActivity extends AppCompatActivity {

    private BookOutlookFragment mBookOutlook;
    private DrawerFragment mDrawer;
    private ReplacingButtonFragment mAddRemoveButton;
    private String mCollectionName;
    private Book mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent fromOtherActivity = getIntent();
        mBook = (Book) fromOtherActivity.getSerializableExtra(StringConstants.BOOK);
        mCollectionName = fromOtherActivity.getStringExtra(StringConstants.COLLECTION_NAME);

        if (!mCollectionName.equals(StringConstants.READ_LIST)) {

            mAddRemoveButton = ReplacingButtonFragment.newInstance();
            mAddRemoveButton.setCurrentBook(mBook);
            mAddRemoveButton.setBookCollection(mCollectionName);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.replacing_button, mAddRemoveButton)
                    .commit();


        } else {
            setContentView(R.layout.activity_one_button_book_details);
        }

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

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }

    public void removeFromReadList(View view) {
        BookManagerApp.getBookRepository(StringConstants.READ_LIST).remove(mBook);
        BookManagerApp.getBookRepository(StringConstants.RECOMMENDATIONS).add(mBook);
        Button button = view.findViewById(R.id.btn_remove);
        button.setVisibility(View.INVISIBLE);
    }
}
