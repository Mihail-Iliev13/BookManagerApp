package com.example.pc.bookmanagerapplication.activities.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.CurrentlyReadingActivity;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.GetRecommendationsActivity;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.ReadBooksActivity;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.WantToReadActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;


public class DrawerFragment extends Fragment {
    private long mCurrentActivityID;
    private android.support.v7.widget.Toolbar mToolbar;

    public DrawerFragment() {
        // Required empty public constructor
    }

    public static DrawerFragment newInstance(){
        return new DrawerFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_drawer, container, false);

        mToolbar = view.findViewById(R.id.tb_menu);

        return view;
    }

    public void setupDrawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem recommendations = new PrimaryDrawerItem()
                .withIdentifier(GetRecommendationsActivity.ID)
                .withName(R.string.recommendations);

        PrimaryDrawerItem toReadList = new PrimaryDrawerItem()
                .withIdentifier(WantToReadActivity.ID)
                .withName(R.string.want_to_read);

        PrimaryDrawerItem readList = new PrimaryDrawerItem()
                .withIdentifier(ReadBooksActivity.ID)
                .withName(R.string.read_books);

        PrimaryDrawerItem currentlyReading = new PrimaryDrawerItem()
                .withIdentifier(CurrentlyReadingActivity.ID)
                .withName(R.string.currently_reading);

        Drawer result = new DrawerBuilder()
                .withActivity(getActivity())
                .withToolbar(mToolbar)
                .addDrawerItems(
                        currentlyReading,
                        new DividerDrawerItem(),
                        toReadList,
                        new DividerDrawerItem(),
                        readList,
                        new DividerDrawerItem(),
                        recommendations
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    long identifier = drawerItem.getIdentifier();


                    if (mCurrentActivityID == identifier) {
                        return false;
                    }

                    Intent intent = getNextIntent(identifier);

                    if (intent == null) {
                        return false;
                    }

                    startActivity(intent);
                    return true;
                })
                .build();
    }

    private Intent getNextIntent(long identifier) {
        if (identifier == CurrentlyReadingActivity.ID) {
            return new Intent(getContext(),
                    CurrentlyReadingActivity.class);
        } else if (identifier == WantToReadActivity.ID) {
            return new Intent(getContext(),
                    WantToReadActivity.class);
        } else if (identifier == ReadBooksActivity.ID){
            return new Intent(getContext(), ReadBooksActivity.class);
        } else if (identifier == GetRecommendationsActivity.ID) {
            return new Intent(getContext(), GetRecommendationsActivity.class);
        } else {
            return null;
        }
    }

    public void setID(long ID){
        mCurrentActivityID = ID;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
