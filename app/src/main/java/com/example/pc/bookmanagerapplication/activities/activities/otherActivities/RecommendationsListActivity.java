package com.example.pc.bookmanagerapplication.activities.activities.otherActivities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pc.bookmanagerapplication.R;
import com.example.pc.bookmanagerapplication.activities.BookManagerApp;
import com.example.pc.bookmanagerapplication.activities.StringConstants;
import com.example.pc.bookmanagerapplication.activities.models.Book;
import com.example.pc.bookmanagerapplication.activities.repository.BookRepository;
import com.example.pc.bookmanagerapplication.activities.repository.base.Repository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


//TODO: make firebase repository, filter the books in it by selected genres and display it

public class RecommendationsListActivity extends AppCompatActivity {
    ListView mRecommendations;
    MyArrayAdapter mArrayAdapter;
    List<String> mBookDetails;
    List<String> mSelectedOptions;
    Repository<Book> mBookRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations_list);
        mBookRepository = BookManagerApp.getBookRepository(StringConstants.COLLECTION_RECOMMENDATIONS);
        mBookRepository.getAll(books -> {
            for (Book book : books) {
            }
        });

//        Intent intent = getIntent();
//        ArrayList<String> sre = (ArrayList<String>) intent.getSerializableExtra("SELECTED_GENRES");

        mRecommendations = findViewById(R.id.lv_recommendations);
        mArrayAdapter = new MyArrayAdapter(this, R.layout.recommendation_list_view_layout, mBookDetails);
        mRecommendations.setAdapter(mArrayAdapter);

    }

    private class MyArrayAdapter extends ArrayAdapter<String> {

        private int layout;
        public MyArrayAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent) {
            ViewHolder mViewHolder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                mViewHolder = new ViewHolder();
                mViewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                mViewHolder.author = (TextView) convertView.findViewById(R.id.tv_author);
                mViewHolder.addButton = (Button) convertView.findViewById(R.id.btn_add);
                convertView.setTag(mViewHolder);

            } else {
               mViewHolder = (ViewHolder) convertView.getTag();
               mViewHolder.title.setText(getItem(position));

            }
            return convertView;
        }
    }

    private class ViewHolder{
        TextView title;
        TextView author;
        Button addButton;
    }
}
