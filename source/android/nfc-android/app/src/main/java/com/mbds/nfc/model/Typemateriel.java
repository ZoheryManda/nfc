package com.mbds.nfc.model;


import java.io.Serializable;

/**
 * Created by macbook on 05/04/2016.
 */
public class Typemateriel implements Serializable {
    private Integer idtype;
    private String valeur;
    private String description;

    public Typemateriel() {
    }

    public Integer getIdtype() {
        return idtype;
    }

    public void setIdtype(Integer idtype) {
        this.idtype = idtype;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Typemateriel that = (Typemateriel) o;

        if (idtype != null ? !idtype.equals(that.idtype) : that.idtype != null) return false;
        if (valeur != null ? !valeur.equals(that.valeur) : that.valeur != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtype != null ? idtype.hashCode() : 0;
        result = 31 * result + (valeur != null ? valeur.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
