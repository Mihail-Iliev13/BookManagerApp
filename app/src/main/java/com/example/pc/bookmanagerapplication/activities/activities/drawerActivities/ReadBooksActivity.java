package com.example.pc.bookmanagerapplication.activities.activities.drawerActivities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.base.BaseDrawerActivity;

public class ReadBooksActivity extends BaseDrawerActivity {

    public static final long ID = 3;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_books);

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