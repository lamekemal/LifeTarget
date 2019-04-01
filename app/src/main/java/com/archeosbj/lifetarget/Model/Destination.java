package com.archeosbj.lifetarget.Model;

import java.io.Serializable;

/**
 * Created by gab on 3/24/19.
 */

public class Destination  implements Serializable{
    int id;
    String nom;
    String tarif;
    public Destination(String nom, String tarif) {
        this.nom = nom;
        this.tarif = tarif;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setTarif(String tarif) {
        this.tarif = tarif;
    }
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getTarif() {
        return tarif;
    }
}
