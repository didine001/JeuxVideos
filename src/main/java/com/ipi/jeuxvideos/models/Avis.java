package com.ipi.jeuxvideos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "note")
    @NotNull(message = "La note ne peut pas être nul")
    private Integer note;
    @Column(name = "description")
    @NotNull(message = "La description ne peut pas être nul")
    @NotBlank(message = "La description ne peut pas être vide")
    private String description;

    @Column(name = "dateEnvoie")
    @NotNull(message = "La date ne peut pas être null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateEnvoie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Jeu jeu;

    public Avis() {

    }

    public Avis(Integer note, String description, Date dateEnvoie) {
        this.note = note;
        this.description = description;
        this.dateEnvoie = dateEnvoie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Date getDateEnvoie() {
        return dateEnvoie;
    }

    public void setDateEnvoie(Date dateEnvoie) {
        this.dateEnvoie = dateEnvoie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
}
