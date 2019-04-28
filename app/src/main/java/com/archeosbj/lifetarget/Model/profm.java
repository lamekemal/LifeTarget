package com.archeosbj.lifetarget.Model;

import java.io.Serializable;

public class profm implements Serializable {
    public String foremail, imgvar;
    public int id;
    public profm(String foremail, String imgvar,  int id) {
        this.foremail = foremail;
        this.imgvar = imgvar;
        this.id = id;
    }

    public String getForemail() {
        return foremail;
    }

    public Integer getId() {
        return id;
    }
    public void setForemail(String foremail) {
        this.foremail = foremail;
    }

    public String getImgvar() {
        return imgvar;
    }

    public void setImgvar(String imgvar) {
        this.imgvar = imgvar;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public profm() {
    }
}
