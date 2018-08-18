package com.example.pc.bookmanagerapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.models.CircleWithTextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        CircleWithTextView circleWithTextView = findViewById(R.id.cwt_circle);

        circleWithTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GetRecommendationsActivity.class);
                startActivity(intent);
            }
        });

    }
}
