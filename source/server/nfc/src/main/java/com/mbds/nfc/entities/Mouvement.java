package com.mbds.nfc.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by macbook on 05/04/2016.
 */
@Entity
@Table(name = "Mouvement")
public class Mouvement implements Serializable {
    private Integer idmvt;
    private Integer idmateriel;
    private Integer idutilisateur;
    private Date dateemprunt;
    private Date dateremise;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;

    public Mouvement() {
    }

    public Mouvement(Integer idmvt) {
        this.idmvt = idmvt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmvt", nullable = false)
    public Integer getIdmvt() {
        return idmvt;
    }

    public void setIdmvt(Integer idmvt) {
        this.idmvt = idmvt;
    }

    @Basic
    @Column(name = "idmateriel", nullable = false)
    public Integer getIdmateriel() {
        return idmateriel;
    }

    public void setIdmateriel(Integer idmateriel) {
        this.idmateriel = idmateriel;
    }

    @Basic
    @Column(name = "idutilisateur", nullable = false)
    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Column(name = "dateemprunt", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm", timezone = "GMT")
    public Date getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(Date dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    @Column(name = "dateremise", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm", timezone = "GMT")
    public Date getDateremise() {
        return dateremise;
    }

    public void setDateremise(Date dateremise) {
        this.dateremise = dateremise;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mouvement mouvement = (Mouvement) o;

        if (idmvt != null ? !idmvt.equals(mouvement.idmvt) : mouvement.idmvt != null) return false;
        if (idmateriel != null ? !idmateriel.equals(mouvement.idmateriel) : mouvement.idmateriel != null) return false;
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
