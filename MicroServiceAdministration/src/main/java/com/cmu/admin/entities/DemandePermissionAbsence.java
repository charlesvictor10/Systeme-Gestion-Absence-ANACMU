package com.cmu.admin.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class DemandePermissionAbsence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date insertDate;
    private Integer insertUserId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private Integer updateUserId;
    @JoinColumn(name = "absence_id", referencedColumnName = "id", nullable = false)
    @OneToOne
    private Absence absence;
    @CreationTimestamp
    private Date dateDemande;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAcceptation;
    @JoinColumn(name = "etat_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Etat etat;

    public DemandePermissionAbsence() {
    }

    public DemandePermissionAbsence(Absence absence, Date dateDemande, Date dateAcceptation, Etat etat) {
        this.absence = absence;
        this.dateDemande = dateDemande;
        this.dateAcceptation = dateAcceptation;
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

    public Absence getAbsence() {
        return absence;
    }

    public void setAbsence(Absence absence) {
        this.absence = absence;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Date getDateAcceptation() {
        return dateAcceptation;
    }

    public void setDateAcceptation(Date dateAcceptation) {
        this.dateAcceptation = dateAcceptation;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
