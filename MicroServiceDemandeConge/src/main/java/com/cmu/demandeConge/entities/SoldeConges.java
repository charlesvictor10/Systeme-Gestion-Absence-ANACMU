package com.cmu.demandeConge.entities;

import com.cmu.demandeConge.util.JIDate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SoldeConges implements Serializable {
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
    private double nbJourPrisDeductible;
    private double nbJourPrisNonDeductible;
    private double nbCongesReliquat;
    private double nbCongesSupplementaire;
    private double nbCongesEchus;
    private double nbCongesEncours;
    @Temporal(TemporalType.DATE)
    private Date dateDernierCalcul;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePremierCumulCourrus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerierCumulCourrus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date anniv;
    @Temporal(TemporalType.TIMESTAMP)
    private Date annivMois;
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @ManyToOne
    private Agent agent;

    public SoldeConges() {
    }

    public SoldeConges(Agent agent) {
        this.nbCongesAcquis = 0.0;
        this.nbJourPrisDeductible = 0.0;
        this.nbJourPrisNonDeductible = 0.0;
        this.nbCongesReliquat = 0.0;
        this.nbCongesSupplementaire = 0.0;
        this.nbCongesEchus = 0.0;
        this.nbCongesEncours = 0.0;
        this.dateDernierCalcul = JIDate.now();
        this.agent = agent;
    }

    public SoldeConges(double nbCongesAcquis, double nbJourPrisNonDeductible, double nbCongesReliquat) {
        this.nbCongesAcquis = nbCongesAcquis;
        this.nbJourPrisNonDeductible = nbJourPrisNonDeductible;
        this.nbCongesReliquat = nbCongesReliquat;
    }

    public void setValeur(SoldeConges sc) {
        this.nbCongesAcquis = sc.nbCongesAcquis;
        this.nbJourPrisDeductible = sc.nbJourPrisDeductible;
        this.nbJourPrisNonDeductible = sc.nbJourPrisNonDeductible;
        this.nbCongesReliquat = sc.nbCongesReliquat;
        this.nbCongesEchus = sc.nbCongesEchus;
        this.nbCongesEncours = sc.nbCongesEncours;
        this.nbCongesSupplementaire = sc.nbCongesSupplementaire;
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

    public double getNbJourPris() {
        return nbJourPrisDeductible;
    }

    public void setNbJourPris(double nbJourPris) {
        this.nbJourPrisDeductible = nbJourPris;
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

    public Date getDatePremierCumulCourrus() {
        return datePremierCumulCourrus;
    }

    public void setDatePremierCumulCourrus(Date datePremierCumulCourrus) {
        this.datePremierCumulCourrus = datePremierCumulCourrus;
    }

    public Date getDateDerierCumulCourrus() {
        return dateDerierCumulCourrus;
    }

    public void setDateDerierCumulCourrus(Date dateDerierCumulCourrus) {
        this.dateDerierCumulCourrus = dateDerierCumulCourrus;
    }

    public Date getAnniv() {
        return anniv;
    }

    public void setAnniv(Date anniv) {
        this.anniv = anniv;
    }

    public Date getAnnivMois() {
        return annivMois;
    }

    public void setAnnivMois(Date annivMois) {
        this.annivMois = annivMois;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
