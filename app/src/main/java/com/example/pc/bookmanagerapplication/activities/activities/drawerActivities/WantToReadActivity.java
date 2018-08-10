package com.example.pc.bookmanagerapplication.activities.activities.drawerActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.base.BaseDrawerActivity;
import com.example.pc.bookmanagerapplication.activities.fragments.DrawerFragment;

public class WantToReadActivity extends AppCompatActivity {

    public static final long ID = 2;
    Toolbar mToolbar;
    private DrawerFragment mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);

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
