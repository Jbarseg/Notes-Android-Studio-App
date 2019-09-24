package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.podcast;

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

public class PodcastsActivity extends AppCompatActivity {

    private DatabaseReference mPodcastsDatabase;
    private RecyclerView mPodcastsRV;
    private FirebaseRecyclerAdapter<Podcasts, PodcastsActivity.PodcastsViewHolder> mPodcastsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcasts);

        mPodcastsDatabase = FirebaseDatabase.getInstance().getReference().child("Podcasts");
        mPodcastsDatabase.keepSynced(true);

        mPodcastsRV = (RecyclerView) findViewById(R.id.podcasts_recyclerview);

        DatabaseReference mPodcastsRef = FirebaseDatabase.getInstance().getReference().child("Podcasts");
        Query mPodcastsQuery = mPodcastsRef.orderByKey();

        mPodcastsRV.hasFixedSize();
        mPodcastsRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions mNewsOptions = new FirebaseRecyclerOptions.Builder<Podcasts>().setQuery(mPodcastsQuery, Podcasts.class).build();

        mPodcastsAdapter = new FirebaseRecyclerAdapter<Podcasts, PodcastsViewHolder>(mNewsOptions) {
            @Override
            protected void onBindViewHolder(PodcastsActivity.PodcastsViewHolder holder, final int position, final Podcasts podcast) {
                holder.setPodcastDetails(getBaseContext(), podcast.getTitle(), podcast.getDescription(), podcast.getImage(), podcast.getAutor());
            }

            @Override
            public PodcastsActivity.PodcastsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_podcasts_row, parent, false);

                return new PodcastsActivity.PodcastsViewHolder(view);
            }
        };

        mPodcastsRV.setAdapter(mPodcastsAdapter);
    }

    public static class PodcastsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public PodcastsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setPodcastDetails(Context context, String title, String desc, String image, String autor){
            TextView mPodcastsTitle = (TextView) mView.findViewById(R.id.podcasts_title_item);
            mPodcastsTitle.setText(title);
            TextView mPodcastsDescription = (TextView) mView.findViewById(R.id.podcasts_description_item);
            mPodcastsDescription.setText(desc);
            ImageView mPodcastsImage = (ImageView) mView.findViewById(R.id.podcasts_image_item);
            Picasso.get().load(image).into(mPodcastsImage);
            TextView mPodcastsAuthor = (TextView) mView.findViewById(R.id.podcasts_autor_item);
            mPodcastsAuthor.setText(autor);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mPodcastsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPodcastsAdapter.stopListening();
    }
}

