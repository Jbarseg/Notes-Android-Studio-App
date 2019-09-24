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

public class HausVersesActivity extends AppCompatActivity {

    private DatabaseReference mHausVDatabase;
    private RecyclerView mRVHaus;
    private FirebaseRecyclerAdapter<Verses, HausVersesActivity.HausVersesViewHolder> mHausVersesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haus_verses);

        mHausVDatabase = FirebaseDatabase.getInstance().getReference().child("VersesHaus");
        mHausVDatabase.keepSynced(true);

        mRVHaus = (RecyclerView) findViewById(R.id.verses_haus_recyclerview);

        DatabaseReference mHausVRef = FirebaseDatabase.getInstance().getReference().child("VersesHaus");
        Query mHausVQuery = mHausVRef.orderByKey();
        FirebaseRecyclerOptions mHausVOptions = new FirebaseRecyclerOptions.Builder<Verses>().setQuery(mHausVQuery, Verses.class).build();

        mRVHaus.hasFixedSize();
        mRVHaus.setLayoutManager(new LinearLayoutManager(this));

        mHausVersesAdapter = new FirebaseRecyclerAdapter<Verses, HausVersesViewHolder>(mHausVOptions) {

            @Override
            protected void onBindViewHolder(HausVersesViewHolder holder, final int position, final Verses verses) {
                holder.setVerseHDetails(verses.getChapterVerse(), verses.getTextVerse());
            }

            @Override
            public HausVersesActivity.HausVersesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_verses_haus, parent, false);

                return new HausVersesActivity.HausVersesViewHolder(view);
            }
        };
        mRVHaus.setAdapter(mHausVersesAdapter);
    }

    public static class HausVersesViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public HausVersesViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setVerseHDetails(String chapterVerse, String textVerse) {
            TextView item_chapter = (TextView) mView.findViewById(R.id.chapter_verse);
            item_chapter.setText(chapterVerse);
            TextView item_text = (TextView) mView.findViewById(R.id.text_verse);
            item_text.setText(textVerse);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mHausVersesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mHausVersesAdapter.stopListening();
    }
}
