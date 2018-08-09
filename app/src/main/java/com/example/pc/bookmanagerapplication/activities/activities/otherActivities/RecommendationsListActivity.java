package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.fragments.RecommendationsListFragment;

import java.util.HashSet;


//TODO RecommendationList to fragment
public class RecommendationsListActivity extends AppCompatActivity {

    ListView mRecommendations;
    HashSet<String> mSelectedOptions;
    RecommendationsListFragment mListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations_list);
        Intent intent = getIntent();
        mSelectedOptions = (HashSet<String>) intent.getSerializableExtra("SELECTED_GENRES");
        mListFragment = RecommendationsListFragment.newInstance();
        mListFragment.setSelectedOptions(mSelectedOptions);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.lv_content, mListFragment)
                .commit();

    }
}
