package com.example.pc.bookmanagerapplication.activities.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.squareup.picasso.Picasso;


public class BookOutlookFragment extends Fragment {

    Book mBook;

    public BookOutlookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_book_outlook, container, false);
        ImageView imageView = view.findViewById(R.id.iv_book_cover);

        Picasso.with(getContext()).load(mBook.url)
                .resize(400, 800)
                .centerInside()
                .into(imageView);

        TextView title = view.findViewById(R.id.tv_title);
        title.setText(mBook.title);
        TextView author = view.findViewById(R.id.tv_author);
        author.setText(mBook.author);
        TextView resume = view.findViewById(R.id.tv_resume);
        resume.setText(mBook.resume);

        return view;
    }

    public static BookOutlookFragment newInstance() {
        return new BookOutlookFragment();
    }

    public void setBook(Book book) {
        this.mBook = book;
    }
}
