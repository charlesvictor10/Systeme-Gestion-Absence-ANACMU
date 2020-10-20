package com.cmu.demandeConge.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Annee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date insertDate;
    private Date updateDate;
    @Column(name = "insert_user_id", nullable = false, updatable = false)
    private int insertUserId;
    @Column(name = "update_user_id", insertable = false)
    private Integer updateUserId;
    private int annee;
    private boolean estCachee;
    private boolean estCourante;

    public Annee() {
    }

    public Annee(int annee, boolean estCachee, boolean estCourante) {
        this.annee = annee;
        this.estCachee = estCachee;
        this.estCourante = estCourante;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(int insertUserId) {
        this.insertUserId = insertUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public boolean isEstCachee() {
        return estCachee;
    }

    public void setEstCachee(boolean estCachee) {
        this.estCachee = estCachee;
    }

    public boolean isEstCourante() {
        return estCourante;
    }

    public void setEstCourante(boolean estCourante) {
        this.estCourante = estCourante;
    }
}
