package com.ipi.jeuxvideos.repositories;


import com.ipi.jeuxvideos.models.Avis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends CrudRepository<Avis, Long> {

    List<Avis> findAll();

    List<Avis> findTopAvisByJeuIdOrderByIdDesc(Long avisId);
}
