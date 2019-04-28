package com.archeosbj.lifetarget.Model;

import java.io.Serializable;

public class fav implements Serializable {
    int id;
    public String fvuniqid, byemail, boolvar, genre, created_at;

    public fav(int id, String fvuniqid, String byemail, String boolvar, String genre, String created_at) {
        this.id = id;
        this.fvuniqid = fvuniqid;
        this.byemail = byemail;
        this.boolvar = boolvar;
        this.genre = genre;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getFvuniqid() {
        return fvuniqid;
    }

    public String getByemail() {
        return byemail;
    }

    public String getBoolvar() {
        return boolvar;
    }

    public String getGenre() {
        return genre;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFvuniqid(String fvuniqid) {
        this.fvuniqid = fvuniqid;
    }

    public void setByemail(String byemail) {
        this.byemail = byemail;
    }

    public void setBoolvar(String boolvar) {
        this.boolvar = boolvar;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public fav() {
    }
}
