package com.example.demo.controllers;

public class Book{
    public String author;
    public String title;
    public Boolean received = false;

    public Book(String author, String title, Boolean received) {
        this.title=title;
        this.author=author;
        this.received = received;
    }
}
