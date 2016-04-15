package com.mbds.nfc.web.rest;

import com.mbds.nfc.entities.Materiel;
import com.mbds.nfc.services.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by macbook on 07/04/2016.
 */

@RestController
@RequestMapping(value = "/materiels/")
public class MaterielController {
    @Autowired
    private MaterielService materielService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Materiel> getMateriels() {
        return materielService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Materiel materiel) {
        if (materielService.findByUiid(materiel.getUiid()) == null) {
            Materiel m = null;
            try {
                m = materielService.save(materiel);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(m, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("L'uiid existe déjà.", HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/uiid/{uiid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Materiel getMaterielByUiid(@PathVariable("uiid") String uiid) {
        return materielService.findByUiid(uiid);
    }

    @RequestMapping(value = "/uiid/{uiid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMaterielByUiid(@PathVariable("uiid") String uiid) {
        Materiel materiel = materielService.findByUiid(uiid);
        if (materiel == null) {
            return new ResponseEntity<>("La ressource demandée n'existe pas.", HttpStatus.NOT_FOUND);
        } else {
            materielService.delete(materiel);
            return new ResponseEntity<>(materiel, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Materiel getMaterielById(@PathVariable("id") int id) {
        return materielService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMaterielById(@PathVariable("id") int id) {
        Materiel materiel = materielService.findById(id);
        if (materiel == null) {
            return new ResponseEntity<>("La ressource demandée n'existe pas.", HttpStatus.NOT_FOUND);
        } else {
            materielService.delete(materiel);
            return new ResponseEntity<>(materiel, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/check/{uiid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isMaterielExist(@PathVariable("uiid") String uiid) {
        return materielService.checkMaterielExistsByUiid(uiid);
    }

}
