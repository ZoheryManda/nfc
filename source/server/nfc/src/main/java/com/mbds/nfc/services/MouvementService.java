package com.mbds.nfc.services;

import com.mbds.nfc.entities.Mouvement;

import java.util.Date;
import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
public interface MouvementService {
    Mouvement save(Mouvement mouvement);

    List<Mouvement> findAll();

    Mouvement findById(int id);

    void delete(Mouvement mouvement);

    List<Mouvement> findByIdmateriel(Integer idmateriel);

    List<Mouvement> findByIdutilisateur(Integer idutilisateur);

    List<Mouvement> findByDateemprunt(Date dateemprunt);

    List<Mouvement> findByDateremise(Date dateremise);

    Mouvement materielEstEmprunter(int idMateriel, int idutilisateur);

    List<Mouvement> getMaterielsRendus();

    List<Mouvement> getMaterielsNonRendus();
}
