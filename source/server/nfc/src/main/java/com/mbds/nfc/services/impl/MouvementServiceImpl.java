package com.mbds.nfc.services.impl;

import com.mbds.nfc.entities.Mouvement;
import com.mbds.nfc.repositories.MouvementRepository;
import com.mbds.nfc.services.MouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by macbook on 05/04/2016.
 */
@Service
@Transactional
public class MouvementServiceImpl implements MouvementService {
    private static final Logger log = Logger.getLogger(MouvementServiceImpl.class.getName());

    @Autowired
    MouvementRepository mouvementRepository;

    @Override
    public Mouvement save(Mouvement mouvement) {
        return mouvementRepository.save(mouvement);
    }

    @Override
    public List<Mouvement> findAll() {
        return mouvementRepository.findAllByOrderByDateempruntDesc();
    }

    @Override
    public Mouvement findById(int id) {
        return mouvementRepository.findOne(id);
    }

    @Override
    public void delete(Mouvement mouvement) {
        mouvementRepository.delete(mouvement);
    }

    @Override
    public List<Mouvement> findByIdmateriel(Integer idmateriel) {
        return mouvementRepository.findByIdmaterielOrderByDateempruntDesc(idmateriel);
    }

    @Override
    public List<Mouvement> findByIdutilisateur(Integer idutilisateur) {
        return mouvementRepository.findByIdutilisateurOrderByDateempruntDesc(idutilisateur);

    }

    @Override
    public List<Mouvement> findByDateemprunt(Date dateemprunt) {
        return mouvementRepository.findByDateemprunt(dateemprunt);

    }

    @Override
    public List<Mouvement> findByDateremise(Date dateremise) {
        return mouvementRepository.findByDateremise(dateremise);

    }

    @Override
    public Mouvement materielEstEmprunter(int idMateriel, int idutilisateur) {
        List<Mouvement> mouvements = mouvementRepository.findByIdmaterielAndIdutilisateurOrderByDateempruntDesc(idMateriel, idutilisateur);

        log.log(Level.INFO, " Size : " + mouvements.size());
        if ((mouvements != null) && (mouvements.size() > 0)) {
            Mouvement m = mouvements.get(0);
            if (m.getDateremise() == null)
            {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Mouvement> getMaterielsRendus(){
        return mouvementRepository.findByDateremiseIsNotNull();
    }

    @Override
    public List<Mouvement> getMaterielsNonRendus(){
        return mouvementRepository.findByDateremiseIsNull();
    }
}
