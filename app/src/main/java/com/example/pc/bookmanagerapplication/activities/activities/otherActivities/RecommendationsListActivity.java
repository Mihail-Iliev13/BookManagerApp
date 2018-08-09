package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.BookManagerApp;
import com.example.pc.bookmanagerapplication.activities.RecommendationsListFragment;
import com.example.pc.bookmanagerapplication.activities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;


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
