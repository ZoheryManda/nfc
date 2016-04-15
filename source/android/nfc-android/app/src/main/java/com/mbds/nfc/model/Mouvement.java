package com.mbds.nfc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by macbook on 05/04/2016.
 */
public class Mouvement implements Serializable {
    private Integer idmvt;
    private Integer idmateriel;
    private Integer idutilisateur;
    private Date dateemprunt;
    private Date dateremise;

    public Mouvement() {
    }

    public Integer getIdmvt() {
        return idmvt;
    }

    public void setIdmvt(Integer idmvt) {
        this.idmvt = idmvt;
    }

    public Integer getIdmateriel() {
        return idmateriel;
    }

    public void setIdmateriel(Integer idmateriel) {
        this.idmateriel = idmateriel;
    }

    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Date getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(Date dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public Date getDateremise() {
        return dateremise;
    }

    public void setDateremise(Date dateremise) {
        this.dateremise = dateremise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mouvement mouvement = (Mouvement) o;

        if (idmvt != null ? !idmvt.equals(mouvement.idmvt) : mouvement.idmvt != null) return false;
        if (idmateriel != null ? !idmateriel.equals(mouvement.idmateriel) : mouvement.idmateriel != null)
            return false;
        if (idutilisateur != null ? !idutilisateur.equals(mouvement.idutilisateur) : mouvement.idutilisateur != null)
            return false;
        if (dateemprunt != null ? !dateemprunt.equals(mouvement.dateemprunt) : mouvement.dateemprunt != null)
            return false;
        return dateremise != null ? dateremise.equals(mouvement.dateremise) : mouvement.dateremise == null;

    }

    @Override
    public int hashCode() {
        int result = idmvt != null ? idmvt.hashCode() : 0;
        result = 31 * result + (idmateriel != null ? idmateriel.hashCode() : 0);
        result = 31 * result + (idutilisateur != null ? idutilisateur.hashCode() : 0);
        result = 31 * result + (dateemprunt != null ? dateemprunt.hashCode() : 0);
        result = 31 * result + (dateremise != null ? dateremise.hashCode() : 0);
        return result;
    }
}
