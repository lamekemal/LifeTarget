package com.archeosbj.lifetarget.Model;

import java.io.Serializable;

public class Actu  implements Serializable {
    public int id;
    public String title, username, pubdate, userimg, description, pubimg;

    public Actu(int id, String title, String username, String pubdate, String userimg, String description, String pubimg) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.pubdate = pubdate;
        this.userimg = userimg;
        this.description = description;
        this.pubimg = pubimg;
    }

    public Actu() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubimg() {
        return pubimg;
    }

    public void setPubimg(String pubimg) {
        this.pubimg = pubimg;
    }
}
