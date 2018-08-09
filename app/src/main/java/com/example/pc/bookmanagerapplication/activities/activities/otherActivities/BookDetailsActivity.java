package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.fragments.BookOutlookFragment;
import com.example.pc.bookmanagerapplication.activities.models.Book;
//TODO add image, change layout, add "want to read" button, and "read" button, add infromation fragment
public class BookDetailsActivity extends AppCompatActivity {

    BookOutlookFragment mBookOutlook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent fromRecommendationList = getIntent();
        Book book = (Book)fromRecommendationList.getSerializableExtra("BOOK");
        mBookOutlook = BookOutlookFragment.newInstance();
        mBookOutlook.setBook(book);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.book_outlook, mBookOutlook)
                .commit();




    }
}
