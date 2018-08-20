package com.example.pc.bookmanagerapplication.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.bookmanagerapplication.utillities.BookManagerApp;
import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.utillities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.BookDetailsActivity;
import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.repository.base.Repository;


public class AddRemoveButtonsFragment extends Fragment {

    private Book mCurrentBook;
    private Repository<Book> mBookCollection;
    private Button mFirstButton;
    private Button mSecondButton;

    public AddRemoveButtonsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_remove_buttons, container, false);
        mFirstButton = view.findViewById(R.id.btn_button1);
        mSecondButton = view.findViewById(R.id.btn_button2);


        if (mBookCollection.getCollectionName()
                .equals(StringConstants.RECOMMENDATIONS)) {

            setButtonsText(StringConstants.WANT_TO_READ, StringConstants.MARK_AS_READ);

        } else if (mBookCollection.getCollectionName()
                .equals(StringConstants.WANT_TO_READ)) {

            setButtonsText(StringConstants.MARK_AS_READ, StringConstants.REMOVE);

        }


        mFirstButton.setOnClickListener(view1 -> {
            String buttonText = String.valueOf(mFirstButton.getText());

            if (buttonText.equals(StringConstants.WANT_TO_READ)) {
                addToWantToReadList();
            } else {
                markAsRead();
            }
        });

        mSecondButton.setOnClickListener(view12 -> {
            String textString = String.valueOf(mSecondButton.getText());

            if (textString.equals(StringConstants.MARK_AS_READ)) {
                markAsRead();
            } else {
                removeFromList();
            }
        });

        return view;
    }

    public static AddRemoveButtonsFragment newInstance() {
        return new AddRemoveButtonsFragment();
    }

    public void setCurrentBook(Book book){
        mCurrentBook = book;
    }

    public void setBookCollection(String collectionName) {
        mBookCollection = BookManagerApp.getBookRepository(collectionName);
    }

    private void addToWantToReadList() {
        Repository from = mBookCollection;
        Repository to = BookManagerApp.getBookRepository(StringConstants.WANT_TO_READ);

        transferBook(from, to);

        showSuccessfullyAddedMessage(StringConstants.WANT_TO_READ);

    }

    private void showSuccessfullyAddedMessage(String listName) {

        String message = String.format("\"%s\" has been successfully added to \"%s\" list",
                mCurrentBook.getTitle(), listName);

        showToast(message);
    }

    private void markAsRead(){

        Repository from = mBookCollection;
        Repository to = BookManagerApp.getBookRepository(StringConstants.READ_LIST);;

        transferBook(from, to);

        showSuccessfullyAddedMessage(StringConstants.READ_BOOKS);

    }

    private void removeFromList(){

        Repository from = mBookCollection;
        Repository to =  BookManagerApp
                .getBookRepository(StringConstants.RECOMMENDATIONS);

        transferBook(from, to);

        String message = String.format("\"%s\" has been removed from \"Want to read\" list",
                mCurrentBook.getTitle());

        showToast(message);
    }

    private void setButtonsText(String first, String second ){
        mFirstButton.setText(first);
        mSecondButton.setText(second);
    }

    private void transferBook(Repository removeFrom, Repository addTo){

        removeFrom.remove(mCurrentBook);
        addTo.add(mCurrentBook);

        BookDetailsActivity.isButtonClicked = true;

        mFirstButton.setVisibility(View.INVISIBLE);
        mSecondButton.setVisibility(View.INVISIBLE);
    }

    private void showToast(String message) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup)getActivity().findViewById(R.id.ll_toast_root));

        layout.findViewById(R.id.tv_toast_message);
        TextView tv = layout.findViewById(R.id.tv_toast_message);
        tv.setText(message);
        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
