package com.cmu.admin.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CongesNonDeductible implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date insertDate;
    @Column(name = "insert_user_id", nullable = false, updatable = false)
    private int insertUserId;
    private Date updateDate;
    @Column(name = "update_user_id", insertable = false)
    private Integer updateUserId;
    @Column(name = "lib", nullable = false, unique = true, length = 100)
    private String lib;
    @Column(nullable = false)
    private int nbJourConges;

    public CongesNonDeductible() {

    }

    public CongesNonDeductible(String lib, int nbJourConges) {
        this.lib = lib;
        this.nbJourConges = nbJourConges;
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

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public int getNbJourConges() {
        return nbJourConges;
    }

    public void setNbJourConges(int nbJourConges) {
        this.nbJourConges = nbJourConges;
    }
}
