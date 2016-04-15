package com.mbds.nfc.services.impl;


import com.mbds.nfc.entities.Typeutilisateur;
import com.mbds.nfc.repositories.TypeutilisateurRepository;
import com.mbds.nfc.services.TypeutilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
@Service
@Transactional
public class TypeutilisateurServiceImpl implements TypeutilisateurService {
    @Autowired
    TypeutilisateurRepository typeutilisateurRepository;

    @Override
    public Typeutilisateur save(Typeutilisateur typeutilisateur) {
        return typeutilisateurRepository.save(typeutilisateur);
    }

    @Override
    public List<Typeutilisateur> findAll() {
        return typeutilisateurRepository.findAll();
    }

    @Override
    public Typeutilisateur findById(int id) {
        return typeutilisateurRepository.findOne(id);
    }

    @Override
    public void delete(Typeutilisateur typeutilisateur) {
        typeutilisateurRepository.delete(typeutilisateur);
    }
}
