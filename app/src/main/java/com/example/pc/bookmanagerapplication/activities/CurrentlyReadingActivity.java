package com.example.pc.bookmanagerapplication.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.pc.bookmanagerapplication.R;

public class CurrentlyReadingActivity extends BaseDrawerActivity {

    public static final long ID = 1;
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);

        mToolbar = findViewById(R.id.tb_menu);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }

    @Override
    public long getIdentifier() {
        return ID;
    }
}
