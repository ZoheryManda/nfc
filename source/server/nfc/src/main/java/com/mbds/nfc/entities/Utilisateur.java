package com.mbds.nfc.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by macbook on 05/04/2016.
 */
@Entity
@Table(name = "Utilisateur")
public class Utilisateur implements Serializable {
    private Integer idutilisateur;
    private Integer id;
    private String nom;
    private String prenom;
    private String telephone;
    private Date datenaissance;
    private String adresse;
    private String pwd;
    private String regid;


    public Utilisateur() {
    }

    public Utilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutilisateur", nullable = false)
    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @Basic
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = -1)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "prenom", nullable = false, length = -1)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "telephone", nullable = false, length = -1)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "datenaissance", nullable = false)
    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    @Basic
    @Column(name = "adresse", nullable = true, length = -1)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "pwd", nullable = false, length = -1)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "regid", nullable = false, length = -1)
    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilisateur that = (Utilisateur) o;

        if (idutilisateur != null ? !idutilisateur.equals(that.idutilisateur) : that.idutilisateur != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (datenaissance != null ? !datenaissance.equals(that.datenaissance) : that.datenaissance != null)
            return false;
        if (adresse != null ? !adresse.equals(that.adresse) : that.adresse != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;
        return regid != null ? regid.equals(that.regid) : that.regid == null;

    }

    @Override
    public int hashCode() {
        int result = idutilisateur != null ? idutilisateur.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (datenaissance != null ? datenaissance.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (regid != null ? regid.hashCode() : 0);
        return result;
    }
}
