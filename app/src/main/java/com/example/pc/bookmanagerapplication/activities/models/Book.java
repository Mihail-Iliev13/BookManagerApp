package com.example.pc.bookmanagerapplication.activities.models;

public class Book {

    public String title;
    public String author;
    public String genre;
    public String resume;

    public Book() {
        // public constructor is needed for Firebase parsing to work
    }

    public Book (String title, String author, String genre, String resume) {
        this.title  = title;
        this.author = author;
        this.genre = genre;
        this.resume = resume;
    }



}
