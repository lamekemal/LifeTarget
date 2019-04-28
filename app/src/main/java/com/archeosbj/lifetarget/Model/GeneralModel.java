/*
 * Production de Kemal DARA, destinée à une utilisation uniquement professionnel, destinée Exclusivement à LifeTarget. Toutes copies ou reproduction est interdites.
 */
package com.archeosbj.lifetarget.Model;


import java.io.Serializable;

public class GeneralModel implements Serializable {
    public int id;
    public String title, adress, rating, description;
    public String urlimage;
    public String uniqId;


    public GeneralModel(int id, String title, String adress, String rating, String description, String urlimage, String uniqId) {
        this.id = id;
        this.title = title;
        this.adress = adress;
        this.rating = rating;
        this.description = description;
        this.urlimage = urlimage;
        this.uniqId = uniqId;
    }
    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public GeneralModel() {
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
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
