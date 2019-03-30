package com.archeosbj.lifetarget.Model;

public class Trans {
    public String uniqueid,  name,  contact,  service,  description, horaire,  price,  pointfort,  extras,  mail,payement,  primpimage, galeryOne,  galerytwo,
            galerytree,loanbus,  transline,  reserveone,reservetwo,  maxcap,  Modified;
    public int Id;

    public Trans() {
    }

    public Trans(String uniqueid, String name, String contact, String service, String description,
                 String horaire, String price, String pointfort, String extras, String mail,
                 String payement, String primpimage, String galeryOne, String galerytwo, String galerytree,
                 String loanbus, String transline, String reserveone, String reservetwo, String maxcap,
                 String modified, int id) {
        this.uniqueid = uniqueid;
        this.name = name;
        this.contact = contact;
        this.service = service;
        this.description = description;
        this.horaire = horaire;
        this.price = price;
        this.pointfort = pointfort;
        this.extras = extras;
        this.mail = mail;
        this.payement = payement;
        this.primpimage = primpimage;
        this.galeryOne = galeryOne;
        this.galerytwo = galerytwo;
        this.galerytree = galerytree;
        this.loanbus = loanbus;
        this.transline = transline;
        this.reserveone = reserveone;
        this.reservetwo = reservetwo;
        this.maxcap = maxcap;
        Modified = modified;
        Id = id;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPointfort() {
        return pointfort;
    }

    public void setPointfort(String pointfort) {
        this.pointfort = pointfort;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPayement() {
        return payement;
    }

    public void setPayement(String payement) {
        this.payement = payement;
    }

    public String getPrimpimage() {
        return primpimage;
    }

    public void setPrimpimage(String primpimage) {
        this.primpimage = primpimage;
    }

    public String getGaleryOne() {
        return galeryOne;
    }

    public void setGaleryOne(String galeryOne) {
        this.galeryOne = galeryOne;
    }

    public String getGalerytwo() {
        return galerytwo;
    }

    public void setGalerytwo(String galerytwo) {
        this.galerytwo = galerytwo;
    }

    public String getGalerytree() {
        return galerytree;
    }

    public void setGalerytree(String galerytree) {
        this.galerytree = galerytree;
    }

    public String getLoanbus() {
        return loanbus;
    }

    public void setLoanbus(String loanbus) {
        this.loanbus = loanbus;
    }

    public String getTransline() {
        return transline;
    }

    public void setTransline(String transline) {
        this.transline = transline;
    }

    public String getReserveone() {
        return reserveone;
    }

    public void setReserveone(String reserveone) {
        this.reserveone = reserveone;
    }

    public String getReservetwo() {
        return reservetwo;
    }

    public void setReservetwo(String reservetwo) {
        this.reservetwo = reservetwo;
    }

    public String getMaxcap() {
        return maxcap;
    }

    public void setMaxcap(String maxcap) {
        this.maxcap = maxcap;
    }

    public String getModified() {
        return Modified;
    }

    public void setModified(String modified) {
        Modified = modified;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
