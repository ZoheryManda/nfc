package com.mbds.nfc.config;

import com.mbds.nfc.entities.*;
import com.mbds.nfc.services.MaterielService;
import com.mbds.nfc.services.TypematerielService;
import com.mbds.nfc.services.TypeutilisateurService;
import com.mbds.nfc.services.UtilisateurSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.Calendar;

@Component
public class DBInitializer {

    private static Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Value("${init-db:false}")
    private String initDatabase;

    @Autowired
    private TypeutilisateurService typeutilisateurService;

    @Autowired
    private TypematerielService typematerielService;

    @Autowired
    private UtilisateurSevice utilisateurSevice;

    @Autowired
    private MaterielService materielService;

    @PostConstruct
    public void init() {
        try {
            logger.info("############## InitDatabase :" + initDatabase + " ########################");
            if (Boolean.parseBoolean(initDatabase)) {
                initDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDatabase() {

        logger.info("Initializing Database with sample data");

        if (typeutilisateurService.findAll().size() == 0) {
            Typeutilisateur typeutilisateur1 = new Typeutilisateur();
            typeutilisateur1.setVal("Admin");
            typeutilisateur1.setDescr("Adminnisatrateur");
            typeutilisateurService.save(typeutilisateur1);

            Typeutilisateur typeutilisateur2 = new Typeutilisateur();
            typeutilisateur2.setVal("Admin");
            typeutilisateur2.setDescr("Adminnisatrateur");
            typeutilisateurService.save(typeutilisateur2);
        }

        if (typematerielService.findAll().size() == 0) {
            Typemateriel typemateriel1 = new Typemateriel();
            typemateriel1.setValeur("Laptop");
            typemateriel1.setDescription("-");
            typematerielService.save(typemateriel1);

            Typemateriel typemateriel2 = new Typemateriel();
            typemateriel2.setValeur("Projecteur");
            typemateriel2.setDescription("-");
            typematerielService.save(typemateriel2);
        }

        if (utilisateurSevice.findAll().size() == 0) {
            Utilisateur utilisateur1 = new Utilisateur();
            utilisateur1.setId(2);
            utilisateur1.setNom("Doe");
            utilisateur1.setPrenom("John");
            utilisateur1.setTelephone("000000000");
            Calendar calendar = Calendar.getInstance();
            utilisateur1.setDatenaissance(new Date(calendar.getTimeInMillis()));
            utilisateur1.setAdresse("21 Backer Street");
            utilisateur1.setPwd("test");
            utilisateur1.setRegid("c3m5i9Rj-3s:APA91bGzrBMTkkqDOkqCSTm3I69IXv9SJGryZo33Y5G5PqJddUrE_L5Rqb6DQ6iYJYZLG-4QjSDKKDbnwSLpzurhCz_RWsrhGjt99AGluY8XiFsKWb1zSPhq_VExJC1aYZ4LsSj0SQGf");
            utilisateurSevice.save(utilisateur1);
        }

        if (materielService.findAll().size() == 0) {
            Materiel materiel1 = new Materiel();
            materiel1.setIdtype(1);
            materiel1.setLibelle("HP Core i3");
            materiel1.setCodemateriel("HP000023");
            materiel1.setUiid("04040ab27a4881");
            try {
                materielService.save(materiel1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Mouvement mouvement1 = new Mouvement();
        mouvement1.setIdmateriel(1);
        mouvement1.setIdutilisateur(1);
        mouvement1.setDateemprunt(new Date(Calendar.getInstance().getTimeInMillis()));
        mouvement1.setDateremise(new Date(Calendar.getInstance().getTimeInMillis()));
    }
}
