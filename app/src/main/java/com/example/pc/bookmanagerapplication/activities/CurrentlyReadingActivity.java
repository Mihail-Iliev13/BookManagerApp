package com.example.pc.bookmanagerapplication.activities.activities.drawerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.base.BaseDrawerActivity;
import com.example.pc.bookmanagerapplication.activities.fragments.DrawerFragment;

public class CurrentlyReadingActivity extends AppCompatActivity {

    public static final long ID = 1;
    private DrawerFragment mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);

        mDrawer = DrawerFragment.newInstance();
        mDrawer.setID(ID);

        setSupportActionBar(mDrawer.getToolbar());

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();


        Intent intent = getIntent();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }
}
