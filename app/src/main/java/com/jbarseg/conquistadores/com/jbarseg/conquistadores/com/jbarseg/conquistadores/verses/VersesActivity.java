package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.verses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jbarseg.conquistadores.R;

public class VersesActivity extends AppCompatActivity {

    private TextView weeklyVerse;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verses);

        weeklyVerse = (TextView) findViewById(R.id.weekly_verse);

        DocumentReference weeklyRef = firestore.collection("VERSES").document("texts");
        weeklyRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();

                    StringBuilder stringBuilder = new StringBuilder("");
                    stringBuilder.append(doc.get("weeklyVerse"));
                    String weeklyVerseString = stringBuilder.toString();
                    weeklyVerse.setText(weeklyVerseString);
                }
            }
        });
    }

    public void showHausVerses(View view) {
        Intent intent = new Intent(VersesActivity.this, HausVersesActivity.class);
        startActivity(intent);
    }
    public void showLaReuVerses(View view) {
        Intent intent = new Intent(VersesActivity.this, LaReuVersesActivity.class);
        startActivity(intent);
    }
    public void showYoungVerses(View view) {
        Intent intent = new Intent(VersesActivity.this, YoungVersesActivity.class);
        startActivity(intent);
    }
}
