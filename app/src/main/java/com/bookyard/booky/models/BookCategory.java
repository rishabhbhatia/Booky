package com.bookyard.booky.models;

import java.util.List;

/**
 * Created by Rishabh Bhatia on 6/17/2016.
 */
public class BookCategory {

    private String imageUrl;
    private String name;
    private String popularAuthors;
    private List<Book> books;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPopularAuthors() {
        return popularAuthors;
    }

    public void setPopularAuthors(String popularAuthors) {
        this.popularAuthors = popularAuthors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}