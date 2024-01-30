package com.ipi.jeuxvideos.repositories;

import com.ipi.jeuxvideos.models.Jeu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JeuRepository extends CrudRepository<Jeu, Long> {
    @Override
    List<Jeu> findAll();
}
