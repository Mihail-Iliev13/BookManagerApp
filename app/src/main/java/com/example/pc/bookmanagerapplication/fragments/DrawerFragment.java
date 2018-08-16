package com.example.pc.bookmanagerapplication.fragments;


import android.accounts.Account;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.GetRecommendationsActivity;
import com.example.pc.bookmanagerapplication.activities.ReadBooksActivity;
import com.example.pc.bookmanagerapplication.activities.WantToReadActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
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
        View view =  inflater.inflate(R.layout.fragment_drawer, container, false);

        mToolbar = view.findViewById(R.id.tb_menu);

        return view;
    }

    public void setupDrawer() {

        PrimaryDrawerItem recommendations = new PrimaryDrawerItem()
                .withIdentifier(GetRecommendationsActivity.ID)
                .withIcon(R.drawable.ic_help_black_24dp)
                .withName(R.string.recommendations);

        PrimaryDrawerItem toReadList = new PrimaryDrawerItem()
                .withIdentifier(WantToReadActivity.ID)
                .withName(R.string.want_to_read)
                .withIcon(R.drawable.ic_local_library_black_24dp);

        PrimaryDrawerItem readList = new PrimaryDrawerItem()
                .withIdentifier(ReadBooksActivity.ID)
                .withName(R.string.read_books)
                .withIcon(R.drawable.ic_check_circle_black_24dp);

        Drawer result = new DrawerBuilder()
                .withActivity(getActivity())
                .withToolbar(mToolbar)
                .addDrawerItems(
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

        if (identifier == WantToReadActivity.ID) {

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
