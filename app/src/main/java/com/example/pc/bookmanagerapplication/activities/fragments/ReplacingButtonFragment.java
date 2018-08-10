package com.example.pc.bookmanagerapplication.activities.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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
    private Repository<Book> mBookRepository;
    private Button mReplacingButton;

    public ReplacingButtonFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_replacing_button, container, false);
        mBookRepository = BookManagerApp.getBookRepository(StringConstants.COLLECTION_WANT_TO_READ);
        mReplacingButton = view.findViewById(R.id.btn_replacing);

        if (!mBookRepository.contains(mCurrentBook)) {
            mReplacingButton.setText(WANT_TO_READ);
        } else {
            mReplacingButton.setText("Remove");
            mReplacingButton.setBackgroundColor(Color.RED);
        }


        mReplacingButton.setOnClickListener(view1 -> {
            String textString = String.valueOf(mReplacingButton.getText());;
            if (textString.equals(WANT_TO_READ)) {

                mBookRepository.add(mCurrentBook);
                mReplacingButton.setText("Remove");
                mReplacingButton.setBackgroundColor(R.color.colorAccent);
                StringBuilder showMessage = new StringBuilder();
                showMessage.append(mCurrentBook.title);
                showMessage.append(" has been added to \"Want to read!\"");
                Toast.makeText(getContext(), showMessage.toString(), Toast.LENGTH_SHORT).show();

            } else {
                mBookRepository.remove(mCurrentBook);
                mReplacingButton.setText(WANT_TO_READ);
                mReplacingButton.setBackgroundColor(R.color.colorLightBlue);
                StringBuilder showMessage = new StringBuilder();
                showMessage.append(mCurrentBook.title);

                showMessage.append(" has been removed from \"Want to read!\"");
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
}
