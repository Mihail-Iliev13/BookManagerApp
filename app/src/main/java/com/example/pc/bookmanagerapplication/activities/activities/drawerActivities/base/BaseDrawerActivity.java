package com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.CurrentlyReadingActivity;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.GetRecommendationsActivity;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.ReadBooksActivity;
import com.example.pc.bookmanagerapplication.activities.activities.drawerActivities.WantToReadActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public abstract class BaseDrawerActivity extends AppCompatActivity {

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

        PrimaryDrawerItem currentlyReading = new PrimaryDrawerItem();
        currentlyReading.withIdentifier(CurrentlyReadingActivity.ID);
        currentlyReading.withName(R.string.currently_reading);

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getDrawerToolbar())
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

                    if (getIdentifier() == identifier) {
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
            return new Intent(BaseDrawerActivity.this,
                    CurrentlyReadingActivity.class);
        } else if (identifier == WantToReadActivity.ID) {
            return new Intent(BaseDrawerActivity.this,
                    WantToReadActivity.class);
        } else if (identifier == ReadBooksActivity.ID){
            return new Intent(BaseDrawerActivity.this, ReadBooksActivity.class);
        } else if (identifier == GetRecommendationsActivity.ID) {
            return new Intent(BaseDrawerActivity.this, GetRecommendationsActivity.class);
        } else {
            return null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    protected abstract Toolbar getDrawerToolbar();

    public abstract long getIdentifier();

}
