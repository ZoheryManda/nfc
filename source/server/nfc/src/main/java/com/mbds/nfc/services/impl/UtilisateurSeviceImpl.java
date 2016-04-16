package com.mbds.nfc.services.impl;

import com.mbds.nfc.entities.Utilisateur;
import com.mbds.nfc.repositories.UtilisateurRepository;
import com.mbds.nfc.services.UtilisateurSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
@Service
@Transactional
public class UtilisateurSeviceImpl implements UtilisateurSevice {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur findById(int id) {
        return utilisateurRepository.findOne(id);
    }

    @Override
    public void delete(Utilisateur utilisateur) {
        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public Utilisateur findByNomAndPrenom(Utilisateur utilisateur) {
        return utilisateurRepository.findByNomAndPrenom(utilisateur.getNom(), utilisateur.getPrenom());
    }

    @Override
    public Utilisateur login(int id, String pwd) {
        return utilisateurRepository.findByIdutilisateurAndPwd(id, pwd);
    }

    @Override
    public Utilisateur login(String nom, String pwd) {
        return utilisateurRepository.findByNomAndPrenom(nom, pwd);
    }

    @Override
    public List<Utilisateur> listeEtudiants(){
        return utilisateurRepository.findById(2);
    }
}