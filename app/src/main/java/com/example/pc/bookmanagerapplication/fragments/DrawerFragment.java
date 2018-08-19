package com.example.pc.bookmanagerapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.utillities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.GetRecommendationsActivity;
import com.example.pc.bookmanagerapplication.activities.BookListActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;


public class DrawerFragment extends Fragment {
    private long mCurrentActivityID;
    private android.support.v7.widget.Toolbar mToolbar;
    public static final long WANT_TO_READ_ID = 2;
    public static final long READ_BOOKS_LIST_ID = 3;
    public static final long GET_RECOMMENDATIONS_ID = 4;

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
                .withIdentifier(GET_RECOMMENDATIONS_ID)
                .withIcon(R.drawable.ic_help_black_24dp)
                .withName(R.string.recommendations);

        PrimaryDrawerItem toReadList = new PrimaryDrawerItem()
                .withIdentifier(WANT_TO_READ_ID)
                .withName(R.string.want_to_read)
                .withIcon(R.drawable.ic_local_library_black_24dp);

        PrimaryDrawerItem readList = new PrimaryDrawerItem()
                .withIdentifier(READ_BOOKS_LIST_ID)
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

        if (identifier == WANT_TO_READ_ID) {

            Intent intent = new Intent(getContext(), BookListActivity.class);
            intent.putExtra(StringConstants.COLLECTION_NAME ,StringConstants.WANT_TO_READ);
            intent.putExtra(StringConstants.CURRENT_ID, WANT_TO_READ_ID);

            return intent;

        } else if (identifier == READ_BOOKS_LIST_ID){

            Intent intent = new Intent(getContext(), BookListActivity.class);
            intent.putExtra(StringConstants.COLLECTION_NAME, StringConstants.READ_LIST);
            intent.putExtra(StringConstants.CURRENT_ID, READ_BOOKS_LIST_ID);

            return intent;

        } else if (identifier == GET_RECOMMENDATIONS_ID) {

            Intent intent = new Intent(getContext(), GetRecommendationsActivity.class);
            intent.putExtra(StringConstants.CURRENT_ID, GET_RECOMMENDATIONS_ID);
            return intent;

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
