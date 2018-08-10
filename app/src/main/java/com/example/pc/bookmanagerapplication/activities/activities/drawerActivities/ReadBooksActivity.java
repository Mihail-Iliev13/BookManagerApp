package com.example.pc.bookmanagerapplication.activities.activities.drawerActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.base.BaseDrawerActivity;
import com.example.pc.bookmanagerapplication.activities.fragments.DrawerFragment;

public class ReadBooksActivity extends AppCompatActivity {

    public static final long ID = 3;
    Toolbar mToolbar;
    DrawerFragment mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_books);

        mDrawer = DrawerFragment.newInstance();
        mDrawer.setID(ID);
        setSupportActionBar(mDrawer.getToolbar());

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }
}
