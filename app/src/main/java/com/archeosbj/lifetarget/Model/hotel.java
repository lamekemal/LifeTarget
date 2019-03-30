package com.archeosbj.lifetarget.Model;

import java.io.Serializable;

public class hotel implements Serializable {
    public int id;
    public String title, adress, payement, siteweb, description, longdescription, uniqueid, rating, service, pointfort, pointfaible, prinpimage, galeryOne, galerytwo, galeryfor, galeryfive, galerysix, mets, modified;

    public hotel(int id, String title, String adress, String payement, String siteweb, String description, String longdescription, String uniqueid, String rating, String service, String pointfort, String pointfaible, String prinpimage, String galeryOne, String galerytwo, String galeryfor, String galeryfive, String galerysix, String mets, String modified) {
        this.id = id;
        this.title = title;
        this.adress = adress;
        this.payement = payement;
        this.siteweb = siteweb;
        this.description = description;
        this.longdescription = longdescription;
        this.uniqueid = uniqueid;
        this.rating = rating;
        this.service = service;
        this.pointfort = pointfort;
        this.pointfaible = pointfaible;
        this.prinpimage = prinpimage;
        this.galeryOne = galeryOne;
        this.galerytwo = galerytwo;
        this.galeryfor = galeryfor;
        this.galeryfive = galeryfive;
        this.galerysix = galerysix;
        this.mets = mets;
        this.modified = modified;
    }

    public hotel() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAdress() {
        return adress;
    }

    public String getPayement() {
        return payement;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public String getDescription() {
        return description;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public String getRating() {
        return rating;
    }

    public String getService() {
        return service;
    }

    public String getPointfort() {
        return pointfort;
    }

    public String getPointfaible() {
        return pointfaible;
    }

    public String getPrinpimage() {
        return prinpimage;
    }

    public String getGaleryOne() {
        return galeryOne;
    }

    public String getGalerytwo() {
        return galerytwo;
    }

    public String getGaleryfor() {
        return galeryfor;
    }

    public String getGaleryfive() {
        return galeryfive;
    }

    public String getGalerysix() {
        return galerysix;
    }

    public String getMets() {
        return mets;
    }

    public String getModified() {
        return modified;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPayement(String payement) {
        this.payement = payement;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setPointfort(String pointfort) {
        this.pointfort = pointfort;
    }

    public void setPointfaible(String pointfaible) {
        this.pointfaible = pointfaible;
    }

    public void setPrinpimage(String prinpimage) {
        this.prinpimage = prinpimage;
    }

    public void setGaleryOne(String galeryOne) {
        this.galeryOne = galeryOne;
    }

    public void setGalerytwo(String galerytwo) {
        this.galerytwo = galerytwo;
    }

    public void setGaleryfor(String galeryfor) {
        this.galeryfor = galeryfor;
    }

    public void setGaleryfive(String galeryfive) {
        this.galeryfive = galeryfive;
    }

    public void setGalerysix(String galerysix) {
        this.galerysix = galerysix;
    }

    public void setMets(String mets) {
        this.mets = mets;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
