package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jbarseg.conquistadores.R;


public class NotesActivity extends AppCompatActivity {

    DatabaseReference mNotesDatabase;
    FloatingActionButton newNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        mNotesDatabase = FirebaseDatabase.getInstance().getReference().child("Notes");
        newNoteButton = (FloatingActionButton) findViewById(R.id.new_note_button);

        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, EditNoteActivity.class);
                startActivity(intent);
            }
        });
    }
}