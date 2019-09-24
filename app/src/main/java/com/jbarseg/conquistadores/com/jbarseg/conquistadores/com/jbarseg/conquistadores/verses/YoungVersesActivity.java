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

public class YoungVersesActivity extends AppCompatActivity {

    private DatabaseReference mYoungDatabase;
    private RecyclerView mRVYoung;
    private FirebaseRecyclerAdapter<Verses, YoungVersesActivity.YoungVersesViewHolder> mYoungVersesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_young_verses);

        mYoungDatabase = FirebaseDatabase.getInstance().getReference().child("VersesYoung");
        mYoungDatabase.keepSynced(true);

        mRVYoung = (RecyclerView) findViewById(R.id.verses_young_recyclerview);

        DatabaseReference mYoungVRef = FirebaseDatabase.getInstance().getReference().child("VersesYoung");
        Query mYoungVQuery = mYoungVRef.orderByKey();

        mRVYoung.hasFixedSize();
        mRVYoung.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions mYoungOptions = new FirebaseRecyclerOptions.Builder<Verses>().setQuery(mYoungVQuery, Verses.class).build();

        mYoungVersesAdapter = new FirebaseRecyclerAdapter<Verses, YoungVersesViewHolder>(mYoungOptions) {

            @Override
            protected void onBindViewHolder(YoungVersesActivity.YoungVersesViewHolder holder, final int position, final Verses verses) {
                holder.setDetailsYoungVerse(verses.getChapterVerse(), verses.getTextVerse());
            }

            @Override
            public YoungVersesActivity.YoungVersesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_verses_young, parent, false);

                return new YoungVersesActivity.YoungVersesViewHolder(view);
            }
        };
        mRVYoung.setAdapter(mYoungVersesAdapter);
    }

    public static class YoungVersesViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public YoungVersesViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetailsYoungVerse(String chapterVerse, String textVerse) {
            TextView item_chapter = (TextView) mView.findViewById(R.id.young_chapter_verse);
            item_chapter.setText(chapterVerse);
            TextView item_text = (TextView) mView.findViewById(R.id.young_text_verse);
            item_text.setText(textVerse);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mYoungVersesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mYoungVersesAdapter.stopListening();
    }
}
