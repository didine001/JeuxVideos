package com.ipi.jeuxvideos.controllers;

import com.ipi.jeuxvideos.models.Avis;
import com.ipi.jeuxvideos.models.Jeu;
import com.ipi.jeuxvideos.repositories.AvisRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/avis/")
public class AvisController {
    @Autowired
    private AvisRepository avisRepository;

    @GetMapping(value = "/")
    List<Avis> all() {
        return avisRepository.findAll();
    }

    @GetMapping(value = "/jeuxvideos/{id}")
    List<Avis> getAllAvis(@PathVariable(name = "id", required = true) Jeu jeu) {
        if (jeu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "jeu introuvable"
            );
        } else {
            return avisRepository.findAll();
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Avis> create(@Valid @RequestBody Avis avis, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            avisRepository.save(avis);
            return new ResponseEntity<>(avis, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Avis> update(@PathVariable(name = "id", required = true) Avis avis,
                                       @Valid @RequestBody Avis avisUpdate) {

        avisUpdate.setId(avis.getId());
        avisRepository.save(avisUpdate);
        return new ResponseEntity<>(avis, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable(name = "id", required = false) Avis avis) {
        if (avis == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "jeu introuvable");
        } else {
            avisRepository.delete(avis);
        }
    }
}