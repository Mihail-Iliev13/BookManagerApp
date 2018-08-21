package com.example.pc.bookmanagerapplication.utillities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.models.Book;

public class ToastShower {

    public static void showSuccessfullyAddedMessage(String listName, Book book, Activity activity) {

        String message = String.format("\"%s\" has been successfully added to \"%s\" list",
                book.getTitle(), listName);

        showToastMessage(message, activity);
    }

    public static void showToastMessage(String message, Activity activity) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup)activity.findViewById(R.id.ll_toast_root));

        layout.findViewById(R.id.tv_toast_message);
        TextView tv = layout.findViewById(R.id.tv_toast_message);
        tv.setText(message);
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static void showBookGenre (Book book, Context context) {
        Toast.makeText(
                context,
                "Genre: " + book.getGenre(),
                Toast.LENGTH_SHORT)
                .show();
    }

}
