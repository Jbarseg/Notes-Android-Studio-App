package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.verses;

public class Verses {
    private String chapterVerse, textVerse;

    public Verses() {}

    public Verses(String chapterVerse, String textVerse) {
        this.chapterVerse = chapterVerse;
        this.textVerse = textVerse;
    }

    public String getChapterVerse() {
        return chapterVerse;
    }

    public void setChapterVerse(String chapterVerse) {
        this.chapterVerse = chapterVerse;
    }

    public String getTextVerse() {
        return textVerse;
    }

    public void setTextVerse(String textVerse) {
        this.textVerse = textVerse;
    }
}
