package com.mbds.nfc.repositories;

import com.mbds.nfc.entities.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by macbook on 05/04/2016.
 */
public interface MaterielRepository extends JpaRepository<Materiel, Integer> {
    Materiel findByUiid(String uiid);
}
