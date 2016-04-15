package com.mbds.nfc.services.impl;

import com.mbds.nfc.entities.Materiel;
import com.mbds.nfc.repositories.MaterielRepository;
import com.mbds.nfc.services.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
@Service
public class MaterielServiceImpl implements MaterielService {
    @Autowired
    MaterielRepository materielRepository;

    @Override
    public Materiel save(Materiel materiel) {
        if (checkMaterielExistsByUiid(materiel.getUiid())) {
            throw new RuntimeException("L'uiid " + materiel.getUiid() + " pour le nouveau materiel existe deja.");
        }
        return materielRepository.save(materiel);
    }

    @Override
    public List<Materiel> findAll() {
        return materielRepository.findAll();
    }

    @Override
    public Materiel findById(int id) {
        return materielRepository.findOne(id);
    }

    @Override
    public void delete(Materiel materiel) {
        materielRepository.delete(materiel);
    }

    @Override
    public Materiel findByUiid(String uiid) {
        return materielRepository.findByUiid(uiid);
    }

    @Override
    public boolean checkMaterielExistsByUiid(String uiid) {
        return materielRepository.findByUiid(uiid) != null;
    }

    @Override
    public boolean checkMaterielExistsById(int id) {
        return materielRepository.findOne(id) != null;
    }
}
