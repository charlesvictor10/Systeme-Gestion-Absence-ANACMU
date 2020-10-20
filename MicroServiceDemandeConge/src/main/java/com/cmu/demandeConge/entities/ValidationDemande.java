package com.cmu.demandeConge.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ValidationDemande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date insertDate;
    private int insertUserId;
    private Date updateDate;
    private Integer updateUserId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidation;
    @Column(length = 4028)
    private String observations;
    @JoinColumn(name = "demande_permission_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private DemandePermissionAbsence demandePermissionAbsence;
    private String etatValidation;
    @JoinColumn(name = "niveau_validation_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private NiveauValidation niveauValidation;
    @JoinColumn(name = "validateur_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Agent validateur;

    public ValidationDemande() {
    }

    public ValidationDemande(Date dateValidation, String observations, DemandePermissionAbsence demandePermissionAbsence, String etatValidation, NiveauValidation niveauValidation, Agent validateur) {
        this.dateValidation = dateValidation;
        this.observations = observations;
        this.demandePermissionAbsence = demandePermissionAbsence;
        this.etatValidation = etatValidation;
        this.niveauValidation = niveauValidation;
        this.validateur = validateur;
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

    public int getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(int insertUserId) {
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

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public DemandePermissionAbsence getDemandePermissionAbsence() {
        return demandePermissionAbsence;
    }

    public void setDemandePermissionAbsence(DemandePermissionAbsence demandePermissionAbsence) {
        this.demandePermissionAbsence = demandePermissionAbsence;
    }

    public String getEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(String etatValidation) {
        this.etatValidation = etatValidation;
    }

    public NiveauValidation getNiveauValidation() {
        return niveauValidation;
    }

    public void setNiveauValidation(NiveauValidation niveauValidation) {
        this.niveauValidation = niveauValidation;
    }

    public Agent getValidateur() {
        return validateur;
    }

    public void setValidateur(Agent validateur) {
        this.validateur = validateur;
    }
}
