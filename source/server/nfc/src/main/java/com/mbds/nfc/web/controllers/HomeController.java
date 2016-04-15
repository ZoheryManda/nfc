package com.mbds.nfc.web.controllers;

import com.mbds.nfc.entities.Mouvement;
import com.mbds.nfc.services.MouvementService;
import com.mbds.nfc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    MouvementService mouvementService;

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
