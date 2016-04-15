package com.mbds.nfc.services.impl;

import com.mbds.nfc.entities.Typemateriel;
import com.mbds.nfc.repositories.TypematerielRepository;
import com.mbds.nfc.services.TypematerielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
@Service
@Transactional
public class TypematerielServiceImpl implements TypematerielService {
    @Autowired
    TypematerielRepository typematerielRepository;

    @Override
    public Typemateriel save(Typemateriel typemateriel) {
        return typematerielRepository.save(typemateriel);
    }

    @Override
    public List<Typemateriel> findAll() {
        return typematerielRepository.findAll();
    }

    @Override
    public Typemateriel findById(int id) {
        return typematerielRepository.findOne(id);
    }

    @Override
    public void delete(Typemateriel materiel) {
        typematerielRepository.delete(materiel);
    }
}
