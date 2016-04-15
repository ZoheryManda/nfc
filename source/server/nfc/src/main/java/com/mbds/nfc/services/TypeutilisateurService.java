package com.mbds.nfc.services;

import com.mbds.nfc.entities.Typeutilisateur;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
public interface TypeutilisateurService {
    Typeutilisateur save(Typeutilisateur typeutilisateur);

    List<Typeutilisateur> findAll();

    Typeutilisateur findById(int id);

    void delete(Typeutilisateur typeutilisateur);
}
