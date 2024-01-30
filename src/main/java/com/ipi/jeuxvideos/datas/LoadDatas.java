package com.ipi.jeuxvideos.datas;

import com.ipi.jeuxvideos.models.Avis;
import com.ipi.jeuxvideos.models.Jeu;
import com.ipi.jeuxvideos.repositories.AvisRepository;
import com.ipi.jeuxvideos.repositories.JeuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class LoadDatas {
    private static final Logger log = LoggerFactory.getLogger(LoadDatas.class);

    @Bean
    CommandLineRunner initDatabase(JeuRepository jeuRepository, AvisRepository avisRepository) throws ParseException {
        log.info("launch preloading");
        if (jeuRepository.count() == 0) {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateSortie = formatter.parse("15/07/2017");
            Date dateEnvoie = formatter.parse("15/07/2017");
            Jeu jeu = new Jeu("ItTakesTwo", "jeu de survie a deux", dateSortie);
            Jeu jeu2 = new Jeu("Deceit", "jeu de survie", dateSortie);
            Avis avis1 = new Avis(5, "tres bien", dateEnvoie);
            avisRepository.save(avis1);
            return args -> {
                log.info("Preloading " + jeuRepository.save(jeu) + jeuRepository.save(jeu2));
            };
        } else {
            return args -> {
                log.info("Already initialized");
            };
        }
    }
}

