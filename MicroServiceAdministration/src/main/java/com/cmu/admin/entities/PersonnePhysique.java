package com.cmu.admin.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@DiscriminatorValue("PP")
public class PersonnePhysique extends Personne {
    private String prenom;
    private String nomJeuneFille;
    private String genre;
    @JoinColumn(name = "civilite_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Civilite civilite;
    @JoinColumn(name = "statut_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Statut statut;

    public PersonnePhysique() {
    }

    public PersonnePhysique(String prenom, String nomJeuneFille, Civilite civilite, String genre, Statut statut) {
        this.prenom = prenom;
        this.nomJeuneFille = nomJeuneFille;
        this.civilite = civilite;
        this.genre = genre;
        this.statut = statut;
    }

    public PersonnePhysique(String nom, String surnomSigle, Date dateNaissance, String lieuNaissance, String tel1, String tel2, String adresse1, String adresse2, String adresse3, String email, String prenom, String nomJeuneFille, Civilite civilite, String genre, Statut statut) {
        super(nom, surnomSigle, dateNaissance, lieuNaissance, tel1, tel2, adresse1, adresse2, adresse3, email);
        this.prenom = prenom;
        this.nomJeuneFille = nomJeuneFille;
        this.civilite = civilite;
        this.genre = genre;
        this.statut = statut;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
