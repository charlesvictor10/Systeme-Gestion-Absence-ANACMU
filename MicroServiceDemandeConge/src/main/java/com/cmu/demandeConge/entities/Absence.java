package com.cmu.demandeConge.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Absence implements Serializable {
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
    private int nbJourNonDeductible;
    @JoinColumn(name = "agent_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Agent agent;
    private String typeAbsence;
    @JoinColumn(name = "conges_non_deductible_id", referencedColumnName = "id")
    @ManyToOne
    private CongesNonDeductible congesNonDeductible;
    @Column(name = "etat_absence", nullable = false)
    private String etatAbsence;

    public Absence() {
    }

    public Absence(String motif, Date dateDebutAbsence, Date dateFinAbsence, double nbJourAbsence, double nbJourOuvrable, double nbJourDeductible, int nbJourNonDeductible, Agent agent, String typeAbsence, CongesNonDeductible congesNonDeductible, String etatAbsence) {
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
        this.etatAbsence = etatAbsence;
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

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
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

    public int getNbJourNonDeductible() {
        return nbJourNonDeductible;
    }

    public void setNbJourNonDeductible(int nbJourNonDeductible) {
        this.nbJourNonDeductible = nbJourNonDeductible;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getTypeAbsence() {
        return typeAbsence;
    }

    public void setTypeAbsence(String typeAbsence) {
        this.typeAbsence = typeAbsence;
    }

    public CongesNonDeductible getCongesNonDeductible() {
        return congesNonDeductible;
    }

    public void setCongesNonDeductible(CongesNonDeductible congesNonDeductible) {
        this.congesNonDeductible = congesNonDeductible;
    }

    public String getEtatAbsence() {
        return etatAbsence;
    }

    public void setEtatAbsence(String etatAbsence) {
        this.etatAbsence = etatAbsence;
    }
}
