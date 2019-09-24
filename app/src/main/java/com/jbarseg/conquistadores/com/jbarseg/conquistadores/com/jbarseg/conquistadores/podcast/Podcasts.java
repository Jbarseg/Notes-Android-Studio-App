package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.podcast;

public class Podcasts {
    private String title, description, image, autor, audioLink;

    public Podcasts(String title, String description, String image, String autor, String audioLink) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.autor = autor;
        this.audioLink = audioLink;
    }

    public Podcasts() {}

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void AudioLink(String audio) {
        this.audioLink = audioLink;
    }
}
