package com.mbds.nfc.model;

import java.io.Serializable;

/**
 * Created by macbook on 05/04/2016.
 */
public class Materiel implements Serializable {
    private Integer idmateriel;
    private Integer idtype;
    private String libelle;
    private String codemateriel;
    private String uiid;

    public Integer getIdmateriel() {
        return idmateriel;
    }

    public void setIdmateriel(Integer idmateriel) {
        this.idmateriel = idmateriel;
    }

    public Integer getIdtype() {
        return idtype;
    }

    public void setIdtype(Integer idtype) {
        this.idtype = idtype;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodemateriel() {
        return codemateriel;
    }

    public void setCodemateriel(String codemateriel) {
        this.codemateriel = codemateriel;
    }

    public String getUiid() {
        return uiid;
    }

    public void setUiid(String uiid) {
        this.uiid = uiid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Materiel materiel = (Materiel) o;

        if (idmateriel != null ? !idmateriel.equals(materiel.idmateriel) : materiel.idmateriel != null)
            return false;
        if (idtype != null ? !idtype.equals(materiel.idtype) : materiel.idtype != null)
            return false;
        if (libelle != null ? !libelle.equals(materiel.libelle) : materiel.libelle != null)
            return false;
        if (codemateriel != null ? !codemateriel.equals(materiel.codemateriel) : materiel.codemateriel != null)
            return false;
        if (uiid != null ? !uiid.equals(materiel.uiid) : materiel.uiid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idmateriel != null ? idmateriel.hashCode() : 0;
        result = 31 * result + (idtype != null ? idtype.hashCode() : 0);
        result = 31 * result + (libelle != null ? libelle.hashCode() : 0);
        result = 31 * result + (codemateriel != null ? codemateriel.hashCode() : 0);
        result = 31 * result + (uiid != null ? uiid.hashCode() : 0);
        return result;
    }
}
