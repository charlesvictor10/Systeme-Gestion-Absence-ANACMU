package com.cmu.parametrage.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "pers_type")
public abstract class Personne implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @CreationTimestamp
    private Date insertDate;
    private Date updateDate;
    @Column(name = "insert_user_id", nullable = false, updatable = false)
    private int insertUserId;
    @Column(name = "update_user_id", insertable = false)
    private Integer updateUserId;
    @Column(name = "nom", nullable = false)
    private String nom;
    private String surnomSigle;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String lieuNaissance;
    private String tel1;
    private String tel2;
    private String adresse1;
    private String adresse2;
    private String adresse3;
    @Email
    private String email;

    public Personne() {
    }

    public Personne(String nom, String surnomSigle, Date dateNaissance, String lieuNaissance, String tel1, String tel2, String adresse1, String adresse2, String adresse3, String email) {
        this.nom = nom;
        this.surnomSigle = surnomSigle;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.adresse3 = adresse3;
        this.email = email;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSurnomSigle() {
        return surnomSigle;
    }

    public void setSurnomSigle(String surnomSigle) {
        this.surnomSigle = surnomSigle;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getAdresse3() {
        return adresse3;
    }

    public void setAdresse3(String adresse3) {
        this.adresse3 = adresse3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
