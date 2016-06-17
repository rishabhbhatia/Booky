package com.bookyard.booky.models;

import com.bookyard.booky.utils.Const;

import java.util.ArrayList;

/**
 * Created by Rishabh Bhatia on 6/17/2016.
 */
public class BookCategory {

    private String imageUrl;
    private String name;
    private String popularAuthors;
    private ArrayList<Book> books;

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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public static String getDemoImage(String width, String height,String imageUrl){

        return Const.CLOUDINARY_BASE_URL+"w_"+width+","+"h_"+height+"/"+imageUrl;
    }
}
