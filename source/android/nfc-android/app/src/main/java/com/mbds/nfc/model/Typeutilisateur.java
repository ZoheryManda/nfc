package com.mbds.nfc.model;

import java.io.Serializable;

/**
 * Created by macbook on 05/04/2016.
 */
public class Typeutilisateur implements Serializable {
    private Integer id;
    private String val;
    private String descr;

    public Typeutilisateur() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Typeutilisateur that = (Typeutilisateur) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (val != null ? !val.equals(that.val) : that.val != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (val != null ? val.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        return result;
    }
}
