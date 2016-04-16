package com.mbds.nfc.services;

import com.mbds.nfc.entities.Utilisateur;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
public interface UtilisateurSevice {
    Utilisateur save(Utilisateur utilisateur);

    List<Utilisateur> findAll();

    Utilisateur findById(int id);

    void delete(Utilisateur utilisateur);

    Utilisateur findByNomAndPrenom(Utilisateur utilisateur);

    Utilisateur login(int id, String pwd);

    Utilisateur login(String nom, String pwd);

    List<Utilisateur> listeEtudiants();
}
