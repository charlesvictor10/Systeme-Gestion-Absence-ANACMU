package com.cmu.admin.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Absence extends SoldeConges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date insertDate;
    private Integer insertUserId;
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    private Integer updateUserId;
    private String motif;
    @Temporal(TemporalType.DATE)
    private Date dateDebutAbsence;
    @Temporal(TemporalType.DATE)
    private Date dateFinAbsence;
    @Column(name = "nb_jour_absence", nullable = false)
    private double nbJourAbsence;
    @Column(name = "nb_jour_ouvrable", nullable = false)
    private double nbJourOuvrable;
    private double nbJourDeductible;
    private double nbJourNonDeductible;
    @JoinColumn(name = "agent_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne
    private TypeAbsence typeAbsence;
    @JoinColumn(name = "conges_non_deductible_id", referencedColumnName = "id")
    @ManyToOne
    private CongesNonDeductible congesNonDeductible;
    @JoinColumn(name = "etat_id", referencedColumnName = "id")
    @ManyToOne
    private Etat etat;

    public Absence() {
    }

    public Absence(String motif, Date dateDebutAbsence, Date dateFinAbsence, double nbJourAbsence, double nbJourOuvrable, double nbJourDeductible, double nbJourNonDeductible, Agent agent, TypeAbsence typeAbsence, CongesNonDeductible congesNonDeductible, Etat etat) {
        this.motif = motif;
        this.dateDebutAbsence = dateDebutAbsence;
        this.dateFinAbsence = dateFinAbsence;
        this.nbJourAbsence = nbJourAbsence;
        this.nbJourOuvrable = nbJourOuvrable;
        this.nbJourDeductible = nbJourDeductible;
        this.nbJourNonDeductible = nbJourNonDeductible;
        this.agent = agent;
        this.typeAbsence = typeAbsence;
        this.congesNonDeductible = congesNonDeductible;
        this.etat = etat;
    }

    public Absence(double nbCongesAcquis, double nbJourDejaPris, double nbJourPrisDeductible, double nbJourPrisNonDeductible, double nbCongesReliquat, double nbCongesSupplementaire, double nbCongesEchus, double nbCongesEncours, Date dateDernierCalcul, String motif, Date dateDebutAbsence, Date dateFinAbsence, double nbJourAbsence, double nbJourOuvrable, double nbJourDeductible, double nbJourNonDeductible, Agent agent, TypeAbsence typeAbsence, CongesNonDeductible congesNonDeductible, Etat etat) {
        super(nbCongesAcquis, nbJourDejaPris, nbJourPrisDeductible, nbJourPrisNonDeductible, nbCongesReliquat, nbCongesSupplementaire, nbCongesEchus, nbCongesEncours, dateDernierCalcul);
        this.motif = motif;
        this.dateDebutAbsence = dateDebutAbsence;
        this.dateFinAbsence = dateFinAbsence;
        this.nbJourAbsence = nbJourAbsence;
        this.nbJourOuvrable = nbJourOuvrable;
        this.nbJourDeductible = nbJourDeductible;
        this.nbJourNonDeductible = nbJourNonDeductible;
        this.agent = agent;
        this.typeAbsence = typeAbsence;
        this.congesNonDeductible = congesNonDeductible;
        this.etat = etat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(Integer insertUserId) {
        this.insertUserId = insertUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getDateDebutAbsence() {
        return dateDebutAbsence;
    }

    public void setDateDebutAbsence(Date dateDebutAbsence) {
        this.dateDebutAbsence = dateDebutAbsence;
    }

    public Date getDateFinAbsence() {
        return dateFinAbsence;
    }

    public void setDateFinAbsence(Date dateFinAbsence) {
        this.dateFinAbsence = dateFinAbsence;
    }

    public double getNbJourAbsence() {
        return nbJourAbsence;
    }

    public void setNbJourAbsence(double nbJourAbsence) {
        this.nbJourAbsence = nbJourAbsence;
    }

    public double getNbJourOuvrable() {
        return nbJourOuvrable;
    }

    public void setNbJourOuvrable(double nbJourOuvrable) {
        this.nbJourOuvrable = nbJourOuvrable;
    }

    public double getNbJourDeductible() {
        return nbJourDeductible;
    }

    public void setNbJourDeductible(double nbJourDeductible) {
        this.nbJourDeductible = nbJourDeductible;
    }

    public double getNbJourNonDeductible() {
        return nbJourNonDeductible;
    }

    public void setNbJourNonDeductible(double nbJourNonDeductible) {
        this.nbJourNonDeductible = nbJourNonDeductible;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public TypeAbsence getTypeAbsence() {
        return typeAbsence;
    }

    public void setTypeAbsence(TypeAbsence typeAbsence) {
        this.typeAbsence = typeAbsence;
    }

    public CongesNonDeductible getCongesNonDeductible() {
        return congesNonDeductible;
    }

    public void setCongesNonDeductible(CongesNonDeductible congesNonDeductible) {
        this.congesNonDeductible = congesNonDeductible;
    }
}
