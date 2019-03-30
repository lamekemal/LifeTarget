package com.archeosbj.lifetarget.Model;

import java.io.Serializable;

public class Life implements Serializable {
    public int id;
    public String title, adress, rating, description;

    public Life(int id, String title, String adress, String rating, String description) {
        this.id = id;
        this.title = title;
        this.adress = adress;
        this.rating = rating;
        this.description = description;
    }

    public Life() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
