package com.example.pc.bookmanagerapplication.activities.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
    private Repository<Book> mBookCollectionToAddTo;
    private Repository<Book> mBookCollectionToRemoveFrom;
    private Button mReplacingButton;

    public ReplacingButtonFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_replacing_button, container, false);
        mReplacingButton = view.findViewById(R.id.btn_replacing);


        if (mBookCollectionToRemoveFrom.getCollectionName()
                .equals(StringConstants.COLLECTION_RECOMMENDATIONS)) {

            mReplacingButton.setText(WANT_TO_READ);

        } else if (mBookCollectionToRemoveFrom.getCollectionName()
                .equals(StringConstants.COLLECTION_READ)) {

            mReplacingButton.setText("Remove");

        } else {
            mReplacingButton.setText("Read");
        }


        mReplacingButton.setOnClickListener(view1 -> {
            String textString = String.valueOf(mReplacingButton.getText());

            if (textString.equals("Remove")) {

                mBookCollectionToAddTo.remove(mCurrentBook);
                mBookCollectionToRemoveFrom.add(mCurrentBook);

                mReplacingButton.setText(mBookCollectionToAddTo.getCollectionName());
                mReplacingButton.setBackgroundColor(R.color.colorAccent);

                StringBuilder showMessage = new StringBuilder();
                showMessage.append(mCurrentBook.title);
                showMessage.append(" has been removed from ");
                showMessage.append(mBookCollectionToAddTo.getCollectionName());
                showMessage.append(" list");
                Toast.makeText(getContext(), showMessage.toString(), Toast.LENGTH_SHORT).show();

            } else {
                mBookCollectionToRemoveFrom.remove(mCurrentBook);
                mBookCollectionToAddTo.add(mCurrentBook);

                mReplacingButton.setText("Remove");
                mReplacingButton.setBackgroundColor(R.color.colorLightBlue);
                StringBuilder showMessage = new StringBuilder();
                showMessage.append(mCurrentBook.title);

                showMessage.append(" has been removed added to \"");
                showMessage.append(mBookCollectionToAddTo.getCollectionName());
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
        mBookCollectionToRemoveFrom = BookManagerApp.getBookRepository(collectionName);

        switch (collectionName) {
            case StringConstants.COLLECTION_RECOMMENDATIONS:
                mBookCollectionToAddTo = BookManagerApp.getBookRepository(StringConstants.COLLECTION_WANT_TO_READ);
                break;
            case StringConstants.COLLECTION_WANT_TO_READ:
                mBookCollectionToAddTo = BookManagerApp.getBookRepository(StringConstants.COLLECTION_READ);
                break;
            case StringConstants.COLLECTION_READ:
                mBookCollectionToAddTo = BookManagerApp.getBookRepository(StringConstants.COLLECTION_RECOMMENDATIONS);
                break;
                default:
                    break;
        }
    }
}
