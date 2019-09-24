package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.news;

public class News {
    private String title, desc, image;

    public News(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public News() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

