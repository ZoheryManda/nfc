package com.mbds.nfc.repositories;

import com.mbds.nfc.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByNomAndPrenom(String nom, String prenom);

    Utilisateur findByIdutilisateurAndPwd(int idutilisateur, String pwd);

    Utilisateur findByNomAndPwd(String nom, String pwd);

    List<Utilisateur> findById(int id);
}
