package com.example.pc.bookmanagerapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.bookmanagerapplication.BookManagerApp;
import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.StringConstants;
import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.fragments.BookOutlookFragment;
import com.example.pc.bookmanagerapplication.fragments.DrawerFragment;
import com.example.pc.bookmanagerapplication.fragments.AddRemoveButtonsFragment;

public class BookDetailsActivity extends AppCompatActivity {

    private BookOutlookFragment mBookOutlook;
    private DrawerFragment mDrawer;
    private AddRemoveButtonsFragment mAddRemoveButton;
    private String mCollectionName;
    private Book mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Intent fromOtherActivity = getIntent();

        mBook = (Book) fromOtherActivity
                .getSerializableExtra(StringConstants.BOOK);

        mCollectionName = fromOtherActivity
                .getStringExtra(StringConstants.COLLECTION_NAME);


        if (!mCollectionName.equals(StringConstants.READ_LIST)) {

            mAddRemoveButton = AddRemoveButtonsFragment.newInstance();
            mAddRemoveButton.setCurrentBook(mBook);
            mAddRemoveButton.setBookCollection(mCollectionName);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.replacing_button, mAddRemoveButton)
                    .commit();


        } else {
            /*
            if you came to this activity from ReadBooks activity
            set a layout with only one button (Remove button)
             */

            setContentView(R.layout.activity_one_button_book_details);
        }

        mBookOutlook = BookOutlookFragment.newInstance();
        mBookOutlook.setBook(mBook);

        mDrawer = DrawerFragment.newInstance();
        mDrawer.setID(-1);


        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu, mDrawer)
                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.book_outlook, mBookOutlook)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }


    /*
     This method removes the book from Read List
     and puts it back to Recommendations

     The method is called only when you came to this activity
     fom ReadBooks activity and the onclick listener is defined in
     the layout - activity_one_button_book_details
     */
    public void removeFromReadList(View view) {
        BookManagerApp.getBookRepository(StringConstants.READ_LIST)
                .remove(mBook);

        BookManagerApp.getBookRepository(StringConstants.RECOMMENDATIONS)
                .add(mBook);

        Button button = view.findViewById(R.id.btn_remove);
        button.setVisibility(View.INVISIBLE);
        String message = String.format("%s has been removed from \"Read Books\" list", mBook.title);
        showToast(message);

    }

    public void showToast (String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup)findViewById(R.id.ll_toast_root));

        layout.findViewById(R.id.tv_toast_message);
        TextView tv = layout.findViewById(R.id.tv_toast_message);
        tv.setText(message);
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
