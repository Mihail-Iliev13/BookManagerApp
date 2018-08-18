package com.example.pc.bookmanagerapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.bookmanagerapplication.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter{

    private int layout;

    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mainViewHolder = null;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.bookCover = (ImageView) convertView.findViewById(R.id.iv_book_cover);
            viewHolder.bookTitle = (TextView) convertView.findViewById(R.id.tv_book_title);

            Picasso.with(getContext()).load(((Book)getItem(position)).url)
                    .resize(150, 300)
                    .centerInside()
                    .into(viewHolder.bookCover);

            viewHolder.bookTitle.setText(((Book)getItem(position)).toString());

            convertView.setTag(viewHolder);

        } else {

            mainViewHolder = (ViewHolder) convertView.getTag();

            Picasso.with(getContext()).load(((Book)getItem(position)).url)
                    .resize(150, 300)
                    .centerInside()
                    .into(mainViewHolder.bookCover);

            mainViewHolder.bookTitle.setText(((Book)getItem(position)).toString());

        }

        return convertView;
    }

    public class ViewHolder {

        ImageView bookCover;
        TextView bookTitle;
    }

}
