package com.ipi.jeuxvideos.controllers;

import com.ipi.jeuxvideos.models.Avis;
import com.ipi.jeuxvideos.models.Jeu;
import com.ipi.jeuxvideos.repositories.AvisRepository;
import com.ipi.jeuxvideos.repositories.JeuRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/jeuxvideos/")
public class JeuController {
    @Autowired
    private JeuRepository jeuRepository;
    @Autowired
    private AvisRepository avisRepository;

    @GetMapping(value = "/")
    List<Jeu> all() {
        return jeuRepository.findAll();
    }


    @GetMapping(value = "/avis/{avisId}")
    List<Avis> getLastAvis(@PathVariable(name = "avisId") Long avisId) {
        if (avisId == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID de l'avis introuvable"
            );
        }
        List<Avis> avisList = avisRepository.findTopAvisByJeuIdOrderByIdDesc(avisId);
        return avisList;
    }

    @GetMapping(value = "/{id}")
    Jeu getOne(@PathVariable(name = "id", required = false) Jeu jeu) {
        if (jeu == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jeu introuvable");
        } else {
            return jeu;
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<Jeu> creerJeu(@Valid @RequestBody Jeu jeu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            jeuRepository.save(jeu);
            return new ResponseEntity<>(jeu, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Jeu> update(@PathVariable(name = "id", required = true) Jeu jeu,
                                      @Valid @RequestBody Jeu jeuUpdate) {

        jeuUpdate.setId(jeu.getId());
        jeuRepository.save(jeuUpdate);
        return new ResponseEntity<>(jeu, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable(name = "id", required = false) Jeu jeu) {
        if (jeu == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "jeu introuvable");
        } else {
            jeuRepository.delete(jeu);
        }
    }
}