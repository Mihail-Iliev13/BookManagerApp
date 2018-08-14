package com.example.pc.bookmanagerapplication.activities.activities.drawerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.otherActivities.RecommendationsListActivity;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.base.BaseDrawerActivity;
import com.example.pc.bookmanagerapplication.activities.fragments.DrawerFragment;

import java.util.ArrayList;
import java.util.HashSet;

//TODO: change the style

public class GetRecommendationsActivity extends AppCompatActivity{

    public static final long ID = 4;
    HashSet<String> mSelection;
    DrawerFragment mDrawer;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_recommendations);

        mDrawer = DrawerFragment.newInstance();
        setSupportActionBar(mDrawer.getToolbar());

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();

        mSelection = new HashSet<>();
        mButton = findViewById(R.id.btn_get_recommendations);

        mButton.setOnClickListener(view -> {
            Intent intent =
                    new Intent(GetRecommendationsActivity.this,
                            RecommendationsListActivity.class);

            intent.putExtra("SELECTED_GENRES", mSelection);
            startActivity(intent);
        });
    }


    public void selectItem(View view) {
        CheckBox mCheckBox = (CheckBox) view;
        String mCheckBoxText = String.valueOf(mCheckBox.getText());

        if (mCheckBox.isChecked()) {
            mSelection.add(mCheckBoxText);
        } else {
            mSelection.remove(mCheckBoxText);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }
}