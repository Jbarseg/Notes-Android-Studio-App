package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jbarseg.conquistadores.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {

    FloatingActionButton newNoteButton;
    EditText noteTitle, noteDescription;
    DatabaseReference mNotesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteTitle = findViewById(R.id.title_note);
        noteDescription = findViewById(R.id.description_note);
        newNoteButton = (FloatingActionButton) findViewById(R.id.new_note_button);
        FloatingActionButton addNote = findViewById(R.id.saveButton);
        mNotesDatabase = FirebaseDatabase.getInstance().getReference().child("Notes");

        //Note data is created in the database.
        addNote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = noteTitle.getText().toString();
                String description = noteDescription.getText().toString();
                String noteId;
                Date noteDate = Calendar.getInstance().getTime();
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MMM-yyyy");
                final String formattedDate = dataFormat.format(noteDate);

                if (!title.isEmpty() || !description.isEmpty()){
                    noteId = mNotesDatabase.push().getKey();
                    Note notes = new Note(title, description, noteId, formattedDate);
                    mNotesDatabase.child(noteId).setValue(notes);
                } else {
                    Toast.makeText(getApplication(), getString(R.string.notesFieldsEmpty), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

