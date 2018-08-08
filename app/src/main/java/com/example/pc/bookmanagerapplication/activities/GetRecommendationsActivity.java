package com.example.pc.bookmanagerapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.pc.bookmanagerapplication.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetRecommendationsActivity extends BaseDrawerActivity{

    public static final long ID = 4;
    List<String> mSelection;
    Toolbar mToolbar;
    Button mButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_recommendations);

        mToolbar = findViewById(R.id.tb_menu);
        setSupportActionBar(mToolbar);

        mSelection = new ArrayList<>();
        mButton = (Button) findViewById(R.id.bt_get_recommendations);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetRecommendationsActivity.this, CurrentlyReadingActivity.class);
                intent.putExtra("SELECTED_GENRES", (Serializable) mSelection);
                startActivity(intent);
            }
        });
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }

    @Override
    public long getIdentifier() {
        return ID;
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
}
