package com.example.pc.bookmanagerapplication.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pc.bookmanagerapplication.BookManagerApp;
import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.utillities.StringConstants;
import com.example.pc.bookmanagerapplication.models.Book;
import com.example.pc.bookmanagerapplication.repository.base.Repository;
import com.example.pc.bookmanagerapplication.utillities.ToastShower;


public class AddRemoveButtonsFragment extends Fragment {

    private Book mCurrentBook;
    private Repository<Book> mBookCollection;
    private Button mFirstButton;
    private Button mSecondButton;
    private boolean isButtonClicked;

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
            isButtonClicked = true;
            String buttonText = String.valueOf(mFirstButton.getText());

            if (buttonText.equals(StringConstants.WANT_TO_READ)) {
                addToWantToReadList();
            } else {
                markAsRead();
            }
        });

        mSecondButton.setOnClickListener(view12 -> {
            isButtonClicked = true;
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

        switch (collectionName) {
            case StringConstants.RECOMMENDATIONS:
                mBookCollection = BookManagerApp.getBookRecommendationsCollection();
                return;
            case StringConstants.READ_LIST:
                mBookCollection = BookManagerApp.getReadBooksCollection();
                return;
            case StringConstants.WANT_TO_READ:
                mBookCollection = BookManagerApp.getWantToReadCollection();
                return;
                default:
                    return;
        }
    }

    private void addToWantToReadList() {

        Repository from = mBookCollection;
        Repository to = BookManagerApp.getWantToReadCollection();

        transferBook(from, to);

        ToastShower.showSuccessfullyAddedMessage(StringConstants.WANT_TO_READ,
                mCurrentBook,
                getActivity());

    }

    private void markAsRead(){

        Repository from = mBookCollection;
        Repository to = BookManagerApp.getReadBooksCollection();;

        transferBook(from, to);

        ToastShower.showSuccessfullyAddedMessage(StringConstants.READ_BOOKS,
                mCurrentBook,
                getActivity());
    }

    private void removeFromList(){

        Repository from = mBookCollection;
        Repository to =  BookManagerApp
                .getBookRecommendationsCollection();

        transferBook(from, to);

        String message = String.format("\"%s\" has been removed from \"Want to read\" list",
                mCurrentBook.getTitle());

        ToastShower.showToastMessage(message, getActivity());
    }

    private void setButtonsText(String first, String second ){
        mFirstButton.setText(first);
        mSecondButton.setText(second);
    }

    private void transferBook(Repository removeFrom, Repository addTo){

        removeFrom.remove(mCurrentBook);
        addTo.add(mCurrentBook);

        mFirstButton.setVisibility(View.INVISIBLE);
        mSecondButton.setVisibility(View.INVISIBLE);
    }

    public boolean isButtonCLicked() {
      return isButtonClicked;
    }

}
