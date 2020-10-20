package com.cmu.admin.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class Situation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @CreationTimestamp
    private Date insertDate;
    @Column(name = "insert_user_id", nullable = false, updatable = false)
    private int insertUserId;
    private Date updateDate;
    @Column(name = "update_user_id", insertable = false)
    private Integer updateUserId;
    @Column(nullable = false, unique = true, length = 20)
    private String code;
    @Column(nullable = false, unique = true, length = 100)
    private String lib;
    private String raisonSortie;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortie;
    @ManyToOne
    @JoinColumn(name = "agent_id", referencedColumnName = "id", nullable = false)
    private Agent agent;

    public Situation() {
    }

    public Situation(String code, String lib, String raisonSortie, Date dateSortie, Agent agent) {
        this.code = code;
        this.lib = lib;
        this.raisonSortie = raisonSortie;
        this.dateSortie = dateSortie;
        this.agent = agent;
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

    public String getRaisonSortie() {
        return raisonSortie;
    }

    public void setRaisonSortie(String raisonSortie) {
        this.raisonSortie = raisonSortie;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
