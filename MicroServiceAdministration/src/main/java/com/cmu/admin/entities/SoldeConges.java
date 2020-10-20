package com.cmu.admin.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SoldeConges implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @CreationTimestamp
    private Date insertDate;
    private Integer insertUserId;
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    private Integer updateUserId;
    private double nbCongesAcquis;
    private double nbJourDejaPris;
    private double nbJourPrisDeductible;
    private double nbJourPrisNonDeductible;
    private double nbCongesReliquat;
    private double nbCongesSupplementaire;
    private double nbCongesEchus;
    private double nbCongesEncours;
    @Temporal(TemporalType.DATE)
    private Date dateDernierCalcul;
   /* @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @ManyToOne
    private Agent agent;*/

    public SoldeConges() {
    }

    public SoldeConges(double nbCongesAcquis, double nbJourDejaPris, double nbJourPrisDeductible, double nbJourPrisNonDeductible, double nbCongesReliquat, double nbCongesSupplementaire, double nbCongesEchus, double nbCongesEncours, Date dateDernierCalcul) {
        this.nbCongesAcquis = nbCongesAcquis;
        this.nbJourDejaPris = nbJourDejaPris;
        this.nbJourPrisDeductible = nbJourPrisDeductible;
        this.nbJourPrisNonDeductible = nbJourPrisNonDeductible;
        this.nbCongesReliquat = nbCongesReliquat;
        this.nbCongesSupplementaire = nbCongesSupplementaire;
        this.nbCongesEchus = nbCongesEchus;
        this.nbCongesEncours = nbCongesEncours;
        this.dateDernierCalcul = dateDernierCalcul;
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

    public double getNbCongesAcquis() {
        return nbCongesAcquis;
    }

    public void setNbCongesAcquis(double nbCongesAcquis) {
        this.nbCongesAcquis = nbCongesAcquis;
    }

    public double getNbJourDejaPris() {
        return nbJourDejaPris;
    }

    public void setNbJourDejaPris(double nbJourDejaPris) {
        this.nbJourDejaPris = nbJourDejaPris;
    }

    public double getNbJourPrisDeductible() {
        return nbJourPrisDeductible;
    }

    public void setNbJourPrisDeductible(double nbJourPrisDeductible) {
        this.nbJourPrisDeductible = nbJourPrisDeductible;
    }

    public double getNbJourPrisNonDeductible() {
        return nbJourPrisNonDeductible;
    }

    public void setNbJourPrisNonDeductible(double nbJourPrisNonDeductible) {
        this.nbJourPrisNonDeductible = nbJourPrisNonDeductible;
    }

    public double getNbCongesReliquat() {
        return nbCongesReliquat;
    }

    public void setNbCongesReliquat(double nbCongesReliquat) {
        this.nbCongesReliquat = nbCongesReliquat;
    }

    public double getNbCongesSupplementaire() {
        return nbCongesSupplementaire;
    }

    public void setNbCongesSupplementaire(double nbCongesSupplementaire) {
        this.nbCongesSupplementaire = nbCongesSupplementaire;
    }

    public double getNbCongesEchus() {
        return nbCongesEchus;
    }

    public void setNbCongesEchus(double nbCongesEchus) {
        this.nbCongesEchus = nbCongesEchus;
    }

    public double getNbCongesEncours() {
        return nbCongesEncours;
    }

    public void setNbCongesEncours(double nbCongesEncours) {
        this.nbCongesEncours = nbCongesEncours;
    }

    public Date getDateDernierCalcul() {
        return dateDernierCalcul;
    }

    public void setDateDernierCalcul(Date dateDernierCalcul) {
        this.dateDernierCalcul = dateDernierCalcul;
    }

    /*public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }*/
}
