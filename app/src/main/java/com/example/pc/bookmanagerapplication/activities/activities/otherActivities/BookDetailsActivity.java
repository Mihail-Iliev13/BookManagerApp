package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.models.Book;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent fromRecommendationList = getIntent();
        Book book = (Book)fromRecommendationList.getSerializableExtra("BOOK");
        TextView title = findViewById(R.id.tv_title);
        title.setText(book.title);
        TextView author = findViewById(R.id.tv_author);
        author.setText(book.author);
        TextView resume = findViewById(R.id.tv_resume);
        resume.setText(book.resume);
    }
}
