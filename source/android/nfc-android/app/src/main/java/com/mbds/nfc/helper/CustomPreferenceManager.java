package com.mbds.nfc.helper;

import android.content.SharedPreferences;

import com.mbds.nfc.model.Utilisateur;

/**
 * Created by macbook on 12/04/16.
 */
public class CustomPreferenceManager {

    public static Utilisateur getUtilisateur(SharedPreferences prefs) {
        String nom = prefs.getString("nom", "");
        String prenom = prefs.getString("prenom", "");
        String matricule = prefs.getString("matricule", "");
        String pwd = prefs.getString("pwd", "");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setIdutilisateur(Integer.parseInt(matricule));
        utilisateur.setPwd(pwd);

        return utilisateur;
    }
}
