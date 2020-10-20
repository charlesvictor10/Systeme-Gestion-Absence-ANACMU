package com.cmu.admin.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@DiscriminatorValue("A")
public class Agent extends PersonnePhysique {
    @Column(nullable = false, unique = true)
    @Size(min = 5, max = 5)
    private String matricule;
    @Column(nullable = false, unique = true)
    @Size(min = 13, max = 16)
    private String cni;
    @Temporal(TemporalType.DATE)
    private Date datePriseFonction;
    @Temporal(TemporalType.DATE)
    private Date dateRecrutement;
    private String telProfessionnel;
    private String numeroPoste;
    @Email
    private String emailProfessionnel;
    private String telBureau;
    @JoinColumn(name = "superviseur_id", referencedColumnName = "id")
    @ManyToOne
    private Agent superviseur;
    @JoinColumn(name = "entite_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Entite entite;
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Service service;
    @JoinColumn(name = "fonction_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Fonction fonction;
    @JoinColumn(name = "etat_agent_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agent")
    private EtatAgent etatAgent;

    public Agent() {
    }

    public Agent(String matricule, String cni, Date datePriseFonction, Date dateRecrutement, String telProfessionnel, String numeroPoste, @Email String emailProfessionnel, String telBureau, Agent superviseur, Entite entite, Service service, Fonction fonction) {
        this.matricule = matricule;
        this.cni = cni;
        this.datePriseFonction = datePriseFonction;
        this.dateRecrutement = dateRecrutement;
        this.telProfessionnel = telProfessionnel;
        this.numeroPoste = numeroPoste;
        this.emailProfessionnel = emailProfessionnel;
        this.telBureau = telBureau;
        this.superviseur = superviseur;
        this.entite = entite;
        this.service = service;
        this.fonction = fonction;
    }

    public Agent(String prenom, String nomJeuneFille, Civilite civilite, String genre, Statut statut, String matricule, String cni, Date datePriseFonction, Date dateRecrutement, String telProfessionnel, String numeroPoste, @Email String emailProfessionnel, String telBureau, Agent superviseur, Entite entite, Service service, Fonction fonction) {
        super(prenom, nomJeuneFille, civilite, genre, statut);
        this.matricule = matricule;
        this.cni = cni;
        this.datePriseFonction = datePriseFonction;
        this.dateRecrutement = dateRecrutement;
        this.telProfessionnel = telProfessionnel;
        this.numeroPoste = numeroPoste;
        this.emailProfessionnel = emailProfessionnel;
        this.telBureau = telBureau;
        this.superviseur = superviseur;
        this.entite = entite;
        this.service = service;
        this.fonction = fonction;
    }

    public Agent(String nom, String surnomSigle, Date dateNaissance, String lieuNaissance, String tel1, String tel2, String adresse1, String adresse2, String adresse3, String email, String prenom, String nomJeuneFille, Civilite civilite, String genre, Statut statut, String matricule, String cni, Date datePriseFonction, Date dateRecrutement, String telProfessionnel, String numeroPoste, String emailProfessionnel, String telBureau, Agent superviseur, Entite entite, Service service, Fonction fonction) {
        super(nom, surnomSigle, dateNaissance, lieuNaissance, tel1, tel2, adresse1, adresse2, adresse3, email, prenom, nomJeuneFille, civilite, genre, statut);
        this.matricule = matricule;
        this.cni = cni;
        this.datePriseFonction = datePriseFonction;
        this.dateRecrutement = dateRecrutement;
        this.telProfessionnel = telProfessionnel;
        this.numeroPoste = numeroPoste;
        this.emailProfessionnel = emailProfessionnel;
        this.telBureau = telBureau;
        this.superviseur = superviseur;
        this.entite = entite;
        this.service = service;
        this.fonction = fonction;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public Date getDatePriseFonction() {
        return datePriseFonction;
    }

    public void setDatePriseFonction(Date datePriseFonction) {
        this.datePriseFonction = datePriseFonction;
    }

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public String getTelProfessionnel() {
        return telProfessionnel;
    }

    public void setTelProfessionnel(String telProfessionnel) {
        this.telProfessionnel = telProfessionnel;
    }

    public String getNumeroPoste() {
        return numeroPoste;
    }

    public void setNumeroPoste(String numeroPoste) {
        this.numeroPoste = numeroPoste;
    }

    public String getEmailProfessionnel() {
        return emailProfessionnel;
    }

    public void setEmailProfessionnel(String emailProfessionnel) {
        this.emailProfessionnel = emailProfessionnel;
    }

    public String getTelBureau() {
        return telBureau;
    }

    public void setTelBureau(String telBureau) {
        this.telBureau = telBureau;
    }

    public Agent getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(Agent superviseur) {
        this.superviseur = superviseur;
    }

    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public EtatAgent getEtatAgent() {
        return etatAgent;
    }

    public void setEtatAgent(EtatAgent etatAgent) {
        this.etatAgent = etatAgent;
    }
}
