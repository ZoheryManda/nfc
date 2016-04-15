package com.mbds.nfc.services;

import com.mbds.nfc.entities.Typemateriel;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
public interface TypematerielService {
    Typemateriel save(Typemateriel typemateriel);

    List<Typemateriel> findAll();

    Typemateriel findById(int id);

    void delete(Typemateriel typemateriel);
}
