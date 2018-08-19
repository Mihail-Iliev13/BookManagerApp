package com.example.pc.bookmanagerapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.utillities.StringConstants;
import com.example.pc.bookmanagerapplication.fragments.DrawerFragment;

import java.util.HashSet;


public class GetRecommendationsActivity extends AppCompatActivity{

    private HashSet<String> mSelectedGenres;
    private DrawerFragment mDrawer;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_recommendations);

        mDrawer = DrawerFragment.newInstance();

        mDrawer.setID(getIntent()
                .getLongExtra(StringConstants.CURRENT_ID, 0));

        setSupportActionBar(mDrawer.getToolbar());

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();

        mSelectedGenres = new HashSet<>();
        mButton = findViewById(R.id.btn_get_recommendations);

        mButton.setOnClickListener(view -> {
            Intent intent =
                    new Intent(GetRecommendationsActivity.this,
                            RecommendationsListActivity.class);

            intent.putExtra(StringConstants.SELECTED_GENRES, mSelectedGenres);
            startActivity(intent);
        });
    }

    // this method is called when a checkbox is clicked
    // onclick listener is defined in the layout xml file for this activity
    public void selectItem(View view) {
        CheckBox mCheckBox = (CheckBox) view;
        String mCheckBoxText = String.valueOf(mCheckBox.getText());

        if (mCheckBox.isChecked()) {
            mSelectedGenres.add(mCheckBoxText);
        } else {
            mSelectedGenres.remove(mCheckBoxText);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }
}
