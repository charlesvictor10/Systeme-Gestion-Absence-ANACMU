package com.cmu.demandeConge.entities;

import com.cmu.demandeConge.util.JIDate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Column(name = "etat_demande", nullable = false)
    private String etatDemande;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "demandePermissionAbsence")
    private List<ValidationDemande> validationDemandeList;*/

    public DemandePermissionAbsence() {
    }

    public DemandePermissionAbsence(Absence absence, Date dateDemande, Date dateAcceptation, String etatDemande) {
        this.absence = absence;
        this.dateDemande = dateDemande;
        this.dateAcceptation = dateAcceptation;
        this.etatDemande = etatDemande;
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

    public String getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(String etatDemande) {
        this.etatDemande = etatDemande;
    }

    /*public List<ValidationDemande> getValidationDemandeList() {
        return validationDemandeList;
    }

    public void setValidationDemandeList(List<ValidationDemande> validationDemandeList) {
        this.validationDemandeList = validationDemandeList;
    }

    public void addValidation(ValidationDemande validDem) {
        if (validationDemandeList == null) {
            validationDemandeList = new ArrayList<>();
        }

        validationDemandeList.add(validDem);
    }*/

    public String getStrDateDemande() {
        return JIDate.formatDate(dateDemande);
    }
}
