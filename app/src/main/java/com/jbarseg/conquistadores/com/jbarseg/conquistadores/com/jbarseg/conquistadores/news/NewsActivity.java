package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jbarseg.conquistadores.R;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    private DatabaseReference mNewsDatabase;
    private RecyclerView mNewsRV;
    private FirebaseRecyclerAdapter<News, NewsActivity.NewsViewHolder> mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mNewsDatabase = FirebaseDatabase.getInstance().getReference().child("News");
        mNewsDatabase.keepSynced(true);

        mNewsRV = (RecyclerView) findViewById(R.id.news_recyclerview);

        DatabaseReference newsRef = FirebaseDatabase.getInstance().getReference().child("News");
        Query mNewsQuery = newsRef.orderByKey();

        mNewsRV.hasFixedSize();
        mNewsRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions mNewsOptions = new FirebaseRecyclerOptions.Builder<News>().setQuery(mNewsQuery, News.class).build();

        mNewsAdapter = new FirebaseRecyclerAdapter<News, NewsActivity.NewsViewHolder>(mNewsOptions) {
            @Override
            protected void onBindViewHolder(NewsActivity.NewsViewHolder holder, final int position, final News news) {
                holder.setNewsDetails(getBaseContext(), news.getTitle(), news.getDesc(), news.getImage());
            }

            @Override
            public NewsActivity.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_news_row, parent, false);

                return new NewsActivity.NewsViewHolder(view);
            }
        };

        mNewsRV.setAdapter(mNewsAdapter);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setNewsDetails(Context context, String title, String desc, String image) {
            TextView mNewsTitle = (TextView) mView.findViewById(R.id.news_title_item);
            mNewsTitle.setText(title);
            TextView mNewsDescription = (TextView) mView.findViewById(R.id.news_description_item);
            mNewsDescription.setText(desc);
            ImageView mNewsImage = (ImageView) mView.findViewById(R.id.news_image_item);
            Picasso.get().load(image).into(mNewsImage);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mNewsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mNewsAdapter.stopListening();
    }
}
