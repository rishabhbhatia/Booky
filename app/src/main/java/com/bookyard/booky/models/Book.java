package com.bookyard.booky.models;

import java.util.List;

/**
 * Created by Rishabh Bhatia on 6/17/2016.
 */
public class Book {

    private String imageUrl;
    private List<String> samplePageImages;
    private String name;
    private BookCategory category;
    private String price;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getSamplePageImages() {
        return samplePageImages;
    }

    public void setSamplePageImages(List<String> samplePageImages) {
        this.samplePageImages = samplePageImages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
