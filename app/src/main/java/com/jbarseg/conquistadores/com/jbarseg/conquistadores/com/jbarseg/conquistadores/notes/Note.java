package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.notes;

public class Note {
    private String title, description, noteId, noteDate;

    public Note(String title, String description, String noteId, String noteDate) {
        this.title = title;
        this.description = description;
        this.noteId = noteId;
        this.noteDate = noteDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    @Override
    public String toString() {
        return title;
    }
}
