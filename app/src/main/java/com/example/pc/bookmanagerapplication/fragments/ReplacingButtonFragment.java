package com.example.pc.bookmanagerapplication.activities.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.BookManagerApp;
import com.example.pc.bookmanagerapplication.activities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;


public class ReplacingButtonFragment extends Fragment {

    public static final String WANT_TO_READ = "Want to Read";
    Book mCurrentBook;
    private Repository<Book> mBookCollection;
    private Button mFirstButton;
    private Button mSecondButton;

    public ReplacingButtonFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_replacing_button, container, false);
        mFirstButton = view.findViewById(R.id.btn_button1);
        mSecondButton = view.findViewById(R.id.btn_button2);


        if (mBookCollection.getCollectionName()
                .equals(StringConstants.COLLECTION_RECOMMENDATIONS)) {

            mFirstButton.setText(WANT_TO_READ);
            mSecondButton.setText("Mark as read");

        } else if (mBookCollection.getCollectionName()
                .equals(StringConstants.COLLECTION_WANT_TO_READ)) {

            mFirstButton.setText("Mark as read");
            mSecondButton.setText("Remove");

        }


        mFirstButton.setOnClickListener(view1 -> {
            String textString = String.valueOf(mFirstButton.getText());

            if (textString.equals(WANT_TO_READ)) {
                mBookCollection.remove(mCurrentBook);
                BookManagerApp
                        .getBookRepository(StringConstants.COLLECTION_WANT_TO_READ)
                        .add(mCurrentBook);

                mFirstButton.setVisibility(View.INVISIBLE);
                mSecondButton.setVisibility(View.INVISIBLE);

                StringBuilder showMessage = new StringBuilder();
                showMessage.append(mCurrentBook.title);
                showMessage.append(" has been successfully added to ");
                showMessage.append(StringConstants.COLLECTION_WANT_TO_READ);
                showMessage.append(" list");
                Toast.makeText(getContext(), showMessage.toString(), Toast.LENGTH_SHORT).show();

            } else {
                mBookCollection.remove(mCurrentBook);
                BookManagerApp.getBookRepository(StringConstants.COLLECTION_READ).add(mCurrentBook);

                mFirstButton.setVisibility(View.INVISIBLE);
                mSecondButton.setVisibility(View.INVISIBLE);
                StringBuilder showMessage = new StringBuilder();
                showMessage.append(mCurrentBook.title);
                showMessage.append(" has been successfully added to \"");
                showMessage.append(StringConstants.COLLECTION_READ);
                showMessage.append(" list");
                Toast.makeText(getContext(), showMessage.toString(), Toast.LENGTH_SHORT).show();
            }

        });

        mSecondButton.setOnClickListener(view12 -> {
            String textString = String.valueOf(mSecondButton.getText());

            if (textString.equals("Mark as read")) {
                mBookCollection.remove(mCurrentBook);
                BookManagerApp.getBookRepository(StringConstants.COLLECTION_READ)
                        .add(mCurrentBook);

                mFirstButton.setVisibility(View.INVISIBLE);
                mSecondButton.setVisibility(View.INVISIBLE);
                StringBuilder showMessage = new StringBuilder();
                showMessage.append(mCurrentBook.title);
                showMessage.append(" has been successfully added to \"");
                showMessage.append(StringConstants.COLLECTION_READ);
                showMessage.append(" list");
                Toast.makeText(getContext(), showMessage.toString(), Toast.LENGTH_SHORT).show();

            } else {
                mBookCollection.remove(mCurrentBook);
                BookManagerApp.getBookRepository(StringConstants.COLLECTION_RECOMMENDATIONS)
                        .add(mCurrentBook);

                mFirstButton.setVisibility(View.INVISIBLE);
                mSecondButton.setVisibility(View.INVISIBLE);
                StringBuilder showMessage = new StringBuilder();
                showMessage.append(mCurrentBook.title);
                showMessage.append(" has been removed from \"");
                showMessage.append(StringConstants.COLLECTION_WANT_TO_READ);
                showMessage.append(" list");
                Toast.makeText(getContext(), showMessage.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    public void setCurrentBook(Book book){
        mCurrentBook = book;
    }

    public static ReplacingButtonFragment newInstance() {
        return new ReplacingButtonFragment();
    }

    public void setBookCollection(String collectionName) {
        mBookCollection = BookManagerApp.getBookRepository(collectionName);
    }
}
