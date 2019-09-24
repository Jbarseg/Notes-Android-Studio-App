package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.verses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jbarseg.conquistadores.R;

public class LaReuVersesActivity extends AppCompatActivity {

    private DatabaseReference mReuVDatabase;
    private RecyclerView mRVReu;
    private FirebaseRecyclerAdapter<Verses, LaReuVersesActivity.ReuVersesViewHolder> mReuVersesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_la_reu_verses);

        mReuVDatabase = FirebaseDatabase.getInstance().getReference().child("VersesReu");
        mReuVDatabase.keepSynced(true);

        mRVReu = (RecyclerView) findViewById(R.id.verses_lareu_recyclerview);

        DatabaseReference mReuVRef = FirebaseDatabase.getInstance().getReference().child("VersesReu");
        Query mReuVQuery = mReuVRef.orderByKey();

        mRVReu.hasFixedSize();
        mRVReu.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions mReuVOptions = new FirebaseRecyclerOptions.Builder<Verses>().setQuery(mReuVQuery, Verses.class).build();

        mReuVersesAdapter = new FirebaseRecyclerAdapter<Verses, ReuVersesViewHolder>(mReuVOptions) {

            @Override
            protected void onBindViewHolder(LaReuVersesActivity.ReuVersesViewHolder holder, final int position, final Verses verses) {
                holder.setDetailsReuVerse(verses.getChapterVerse(), verses.getTextVerse());
            }

            @Override
            public LaReuVersesActivity.ReuVersesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_verses_reu, parent, false);

                return new LaReuVersesActivity.ReuVersesViewHolder(view);
            }
        };
        mRVReu.setAdapter(mReuVersesAdapter);
    }

    public static class ReuVersesViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ReuVersesViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetailsReuVerse(String chapterVerse, String textVerse) {
            TextView item_chapter = (TextView) mView.findViewById(R.id.reu_chapter_verse);
            item_chapter.setText(chapterVerse);
            TextView item_text = (TextView) mView.findViewById(R.id.reu_text_verse);
            item_text.setText(textVerse);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mReuVersesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mReuVersesAdapter.stopListening();
    }
}
