package com.mbds.nfc.repositories;

import com.mbds.nfc.entities.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by macbook on 05/04/2016.
 */
public interface MouvementRepository extends JpaRepository<Mouvement, Integer> {
    List<Mouvement> findByIdmateriel(Integer idmateriel);

    List<Mouvement> findByIdutilisateurOrderByDateempruntDesc(Integer idutilisateur);

    List<Mouvement> findByDateemprunt(Date dateemprunt);

    List<Mouvement> findByDateremise(Date dateremise);

    List<Mouvement> findAllByOrderByDateempruntDesc();

    List<Mouvement> findByIdmaterielAndIdutilisateurOrderByDateempruntDesc(Integer idmateriel, Integer idutilisateur);

    List<Mouvement> findByIdmaterielOrderByDateempruntDesc(Integer idmateriel);

    List<Mouvement> findByDateremiseIsNull();

    List<Mouvement> findByDateremiseIsNotNull();
}
