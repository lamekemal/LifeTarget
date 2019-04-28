/*
 * Production de Kemal DARA, destinée à une utilisation uniquement professionnel, destinée Exclusivement à LifeTarget. Toutes copies ou reproduction est interdites.
 */

package com.archeosbj.lifetarget.Model;

import java.io.Serializable;

public class msgm implements Serializable {
    public int id;
    public String onmsg, msg, formsg,ofmsg,created;

    public msgm(int id, String onmsg, String msg, String formsg, String ofmsg, String created) {
        this.id = id;
        this.onmsg = onmsg;
        this.msg = msg;
        this.formsg = formsg;
        this.ofmsg = ofmsg;
        this.created = created;
    }

    public msgm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOnmsg() {
        return onmsg;
    }

    public void setOnmsg(String onmsg) {
        this.onmsg = onmsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFormsg() {
        return formsg;
    }

    public void setFormsg(String formsg) {
        this.formsg = formsg;
    }

    public String getOfmsg() {
        return ofmsg;
    }

    public void setOfmsg(String ofmsg) {
        this.ofmsg = ofmsg;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


}
