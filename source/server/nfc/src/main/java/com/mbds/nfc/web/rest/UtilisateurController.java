package com.mbds.nfc.web.rest;

import com.mbds.nfc.entities.Utilisateur;
import com.mbds.nfc.services.UtilisateurSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by macbook on 07/04/2016.
 */
@RestController
@RequestMapping(value = "/utilisateurs/")
public class UtilisateurController {

    Logger logger = Logger.getLogger(UtilisateurController.class.getName());

    @Autowired
    UtilisateurSevice utilisateurSevice;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Utilisateur> utilisateurs() {
        return utilisateurSevice.findAll();
    }

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Utilisateur utilisateur) {
        logger.info("Login : " + utilisateur.getIdutilisateur() + " " + utilisateur.getPwd());

        Utilisateur utilisateur1 = utilisateurSevice.login(utilisateur.getIdutilisateur(), utilisateur.getPwd());

        if (utilisateur1 == null) {
            return new ResponseEntity<>("Login ou mot de passe erronés.", HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        }
    }
}
