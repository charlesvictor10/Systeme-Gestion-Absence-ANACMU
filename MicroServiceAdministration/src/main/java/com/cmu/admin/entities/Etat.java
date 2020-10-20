package com.cmu.admin.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Etat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date insertDate;
    private Date updateDate;
    @Column(name = "code",nullable = false)
    private String code;
    @Column(name = "lib",nullable = false)
    private String lib;
    private String description;
    private String raisonRefus;

    public Etat() {
    }

    public Etat(String code, String lib, String description) {
        this.code = code;
        this.lib = lib;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRaisonRefus() {
        return raisonRefus;
    }

    public void setRaisonRefus(String raisonRefus) {
        this.raisonRefus = raisonRefus;
    }
}
