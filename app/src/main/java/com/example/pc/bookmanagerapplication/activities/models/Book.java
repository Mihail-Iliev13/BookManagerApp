package com.example.pc.bookmanagerapplication.activities.models;

import java.io.Serializable;

public class Book implements Serializable {

    public String title;
    public String author;
    public String genre;
    public String resume;
    public String url;

    public Book() {
        // public constructor is needed for Firebase parsing to work
    }

    public Book (String title, String author, String genre, String resume, String url) {
        this.title  = title;
        this.author = author;
        this.genre = genre;
        this.resume = resume;
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\"").append(title).append("\"").append(" by ").append(author);
        return builder.toString();
    }
}
