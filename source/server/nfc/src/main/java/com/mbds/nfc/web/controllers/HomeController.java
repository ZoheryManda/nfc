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

    // Historique
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

    // Notifications
    @RequestMapping(value = "/notifications/", method = RequestMethod.GET)
    public String notifier(ModelMap model){
        model.addAttribute("title", "Notifications");
        return "accueil/notifications";
    }

    // Materiels
    @RequestMapping(value = "/materiels/", method = RequestMethod.GET)
    public String listerMateriel(ModelMap model){
        List<Materiel> materiels = materielService.findAll();
        List<Typemateriel> typemateriels = typematerielService.findAll();
        model.addAttribute("title", "Materiels");
        model.addAttribute("subtitle", "Liste des materiels");
        model.addAttribute("materiels", materiels);
        model.addAttribute("typemateriels", typemateriels);
        return "accueil/materiels";
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

    @RequestMapping(value = "/materiels/", method = RequestMethod.POST)
    public String ajoutMateriel(@ModelAttribute Materiel materiel, ModelMap model){
        try {
            materielService.save(materiel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/rest/admin/materiels/";
    }

    @RequestMapping(value = "/materiels/delete/{id}", method = RequestMethod.GET)
    public String supprimerMateriel(@PathVariable("id") int id, ModelMap model){
        materielService.delete(materielService.findById(id));
        return "redirect:/rest/admin/materiels/";
    }

    // Etudiants
    @RequestMapping(value = "/etudiants/", method = RequestMethod.GET)
    public String listerEtudiant(ModelMap model){
        List<Utilisateur> etudiants = utilisateurSevice.listeEtudiants();
        model.addAttribute("title", "Etudiants");
        model.addAttribute("etudiants", etudiants);
        model.addAttribute("subtitle", "Liste des etudiants.");
        return "accueil/etudiants";
    }

    @RequestMapping(value = "/etudiants/", method = RequestMethod.POST)
    public String ajouterEtudiant(@ModelAttribute("utilsateurForm") Utilisateur utilisateur, ModelMap model){
        utilisateur.setId(2);
        Utilisateur u = utilisateurSevice.save(utilisateur);
        String error = "";
        if(u == null){
            error = "Une erreur est survenue lors de l'insertion.";
        }
        model.addAttribute("error", error);
        return "redirect:/rest/admin/etudiants/";
    }

    @RequestMapping(value = "/etudiants/delete/{id}", method = RequestMethod.GET)
    public String supprimerEtudiant(@PathVariable("id") int id, ModelMap model){
        utilisateurSevice.delete(utilisateurSevice.findById(id));
        return "redirect:/rest/admin/etudiants/";
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
