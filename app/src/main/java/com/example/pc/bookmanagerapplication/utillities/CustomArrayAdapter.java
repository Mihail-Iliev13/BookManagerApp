package com.example.pc.bookmanagerapplication.utillities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter{

    private int mLayout;

    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mainViewHolder;
        Book book = (Book) getItem(position);


        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(mLayout, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);

            viewHolder.showBookCover(book);
            viewHolder.showBookTitle(book);

            convertView.setTag(viewHolder);

        } else {

            mainViewHolder = (ViewHolder) convertView.getTag();
            mainViewHolder.showBookCover(book);
            mainViewHolder.showBookTitle(book);
        }

        return convertView;
    }


    private class ViewHolder {

        private ImageView bookCover;
        private TextView bookTitle;

        private ViewHolder () {

        }

        private ViewHolder(View view) {
            this.bookCover = view.findViewById(R.id.iv_book_cover);
            this.bookTitle = view.findViewById(R.id.tv_book_title);

        }

        private void showBookCover (Book book) {

            Picasso.with(getContext()).load(book.getBookCoverUrl())
                    .resize(150, 300)
                    .centerInside()
                    .into(this.bookCover);
        }

        private void showBookTitle(Book book) {
            this.bookTitle.setText(book.toString());
        }

    }
}
