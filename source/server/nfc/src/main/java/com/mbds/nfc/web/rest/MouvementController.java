package com.mbds.nfc.web.rest;

import com.mbds.nfc.entities.Mouvement;
import com.mbds.nfc.entities.Utilisateur;
import com.mbds.nfc.model.Notification;
import com.mbds.nfc.services.GCMNotificationSender;
import com.mbds.nfc.services.MouvementService;
import com.mbds.nfc.services.UtilisateurSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by macbook on 07/04/2016.
 */

@RestController
@RequestMapping(value = "/mouvements/")
public class MouvementController {

    Logger logger = Logger.getLogger(MouvementController.class.getName());
    @Autowired
    MouvementService mouvementService;

    @Autowired
    UtilisateurSevice utilisateurSevice;

    @Autowired
    GCMNotificationSender gcmNotificationSender;


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Mouvement> mouvements() {
        return mouvementService.findAll();
    }

    @RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Mouvement> mouvementsByUtilisateur(@PathVariable("id") int id) {
        return mouvementService.findByIdutilisateur(id);
    }

    @RequestMapping(value = "/materiel/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> mouvement(@RequestBody Mouvement mouvement) {
        Mouvement mvt = mouvementService.materielEstEmprunter(mouvement.getIdmateriel(),mouvement.getIdutilisateur());
        Utilisateur utilisateur = utilisateurSevice.findById(mouvement.getIdutilisateur());
        if (mvt == null) {
            mouvementService.save(mouvement);

            Notification notification = new Notification();
            notification.setTitle("Emprunt");
            notification.setMessage(String.format("Le materiel a éte emprunté par %s le %s.", utilisateur.getNom(), mouvement.getDateemprunt()));
            String[] regids = {utilisateur.getRegid()};
            notification.setRegistrationIdsToSend(regids);
            notification.setBadge(1);
            gcmNotificationSender.sendNotification(notification);

            return new ResponseEntity<>(mouvement, HttpStatus.CREATED);
        } else {
            mvt.setDateremise(mouvement.getDateemprunt());
            mouvementService.save(mvt);

            Notification notification = new Notification();
            notification.setTitle("Remise");
            notification.setMessage(String.format("Le materiel a éte remise par %s le %s.", utilisateur.getNom(), mouvement.getDateemprunt()));
            String[] regids = {utilisateur.getRegid()};
            notification.setRegistrationIdsToSend(regids);
            notification.setBadge(1);
            gcmNotificationSender.sendNotification(notification);

            return new ResponseEntity<>(mvt, HttpStatus.OK);
        }
    }
}
