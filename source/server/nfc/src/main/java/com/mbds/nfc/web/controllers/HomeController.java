package com.mbds.nfc.web.controllers;

import com.mbds.nfc.entities.Materiel;
import com.mbds.nfc.entities.Mouvement;
import com.mbds.nfc.entities.Typemateriel;
import com.mbds.nfc.entities.Utilisateur;
import com.mbds.nfc.model.Notification;
import com.mbds.nfc.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MouvementService mouvementService;

    @Autowired
    private UtilisateurSevice utilisateurSevice;

    @Autowired
    private MaterielService materielService;

    @Autowired
    private TypematerielService typematerielService;

    @Autowired
    private GCMNotificationSender gcmNotificationSender;

    @RequestMapping(value = "/home/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        model.addAttribute("user", userService.findUserById(1).getFirstName());
        return "welcome";
    }

    @RequestMapping(value = "/historique/", method = RequestMethod.GET)
    public String historique(ModelMap model){
        List<Mouvement> mouvements = mouvementService.findAll();

        model.addAttribute("title", "Historique");
        model.addAttribute("subtitle", "Historique des emprunts.");
        model.addAttribute("mouvements", mouvements);

        return "accueil/historique";
    }

    @RequestMapping(value = "/historique/{etat}", method = RequestMethod.GET)
    public String filtreMateriels(@PathVariable("etat") String etat, ModelMap model){
        List<Mouvement> mouvements = new ArrayList<>();
        String subtitle = "";
        if (etat.equalsIgnoreCase(String.valueOf(Etat.EMPRUNTE))){
            mouvements = mouvementService.getMaterielsNonRendus();
            subtitle = "Liste des materiels empruntés.";
        } else if (etat.equalsIgnoreCase(String.valueOf(Etat.RENDU))){
            mouvements = mouvementService.getMaterielsRendus();
            subtitle = "Liste des materiels rendus.";
        }
        model.addAttribute("title", "Historique");
        model.addAttribute("subtitle", subtitle);
        model.addAttribute("mouvements", mouvements);
        return "accueil/historique";
    }

    @RequestMapping(value = "/notifications/", method = RequestMethod.GET)
    public String notifier(ModelMap model){
        model.addAttribute("title", "Notifications");
        return "accueil/notifications";
    }

    @RequestMapping(value = "/materiels/", method = RequestMethod.GET)
    public String listerMateriel(ModelMap model){
        model.addAttribute("title", "Materiels");
        return "accueil/materiels";
    }


    public String listerMaterielEmprunt(){
        return "";
    }

    @RequestMapping(value = "/etudiants/", method = RequestMethod.GET)
    public String listerEtudiant(ModelMap model){
        model.addAttribute("title", "Etudiants");
        return "accueil/etudiants";
    }

    @RequestMapping(value = "/notifications/{idUtilisateur}/{idMateriel}", method = RequestMethod.GET)
    public String notifierRetard(@PathVariable("idUtilisateur") int idUtilisateur,
                                 @PathVariable("idMateriel") int idMateriel) {
        Utilisateur utilisateur = utilisateurSevice.findById(idUtilisateur);
        Materiel materiel = materielService.findById(idMateriel);
        Typemateriel typemateriel = typematerielService.findById(materiel.getIdtype());

        String msg = String.format("Vous devez rendre le materiel %d de type %s au plus tot.", materiel.getIdmateriel(), typemateriel.getValeur());
        String[] regids = {utilisateur.getRegid()};

        Notification notification = new Notification();
        notification.setTitle("Retard");
        notification.setMessage(msg);
        notification.setBadge(1);
        notification.setRegistrationIdsToSend(regids);

        LOG.trace("Sending notification {}", notification);
        gcmNotificationSender.sendNotification(notification);
        return "redirect:/rest/admin/historique/";
    }

    public enum Etat{
        RENDU("rendu"),
        EMPRUNTE("emprunte");

        private String name = "";

        Etat(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
