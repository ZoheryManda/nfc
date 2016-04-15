package com.mbds.nfc.services;

import com.mbds.nfc.entities.Materiel;

import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
public interface MaterielService {
    Materiel save(Materiel materiel) throws Exception;

    List<Materiel> findAll();

    Materiel findById(int id);

    void delete(Materiel materiel);

    Materiel findByUiid(String uiid);

    boolean checkMaterielExistsByUiid(String uiid);

    boolean checkMaterielExistsById(int id);
}
