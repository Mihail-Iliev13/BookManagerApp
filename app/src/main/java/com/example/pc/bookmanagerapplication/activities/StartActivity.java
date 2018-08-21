package com.example.pc.bookmanagerapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.customviews.circlewithtext.CircleWithTextView;

public class StartActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    CircleWithTextView mCircle;
    GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mCircle = findViewById(R.id.cwt_circle);
        mDetector = new GestureDetector(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {

        if (mCircle.isTouched(e)) {
            goToGetRecommendationsActivity();
        }

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float diffX = Math.abs(e1.getX() - e2.getX());
        float diffY = Math.abs(e1.getY() - e2.getY());

        if (diffX > diffY) {

            if (diffX > 0) {
                Intent intent = new Intent(this, GetRecommendationsActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return true;
    }


    private void goToGetRecommendationsActivity(){
        Intent intent = new Intent(StartActivity.this, GetRecommendationsActivity.class);
        startActivity(intent);
        finish();
    }
}

