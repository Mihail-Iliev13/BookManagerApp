package com.example.pc.bookmanagerapplication.activities.activities.drawerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.base.BaseDrawerActivity;

public class CurrentlyReadingActivity extends BaseDrawerActivity {

    public static final long ID = 1;
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);

        mToolbar = findViewById(R.id.tb_menu);
        setSupportActionBar(mToolbar);

        Intent intent = getIntent();

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
