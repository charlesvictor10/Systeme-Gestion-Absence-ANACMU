package com.cmu.admin.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Service implements Serializable {
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
    @Column(name = "lib", nullable = false, unique = true, length = 100)
    private String lib;
    @Column(name = "code", nullable = false, unique = true, length = 20)
    private String code;
    @Column(length = 2048)
    private String description;
    @Column(nullable = false)
    private int niveau;
    @JoinColumn(name = "service_rattachement_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Service serviceRattachement;
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Agent chefDeService;

    public Service() {
    }

    public Service(String lib, String code, String description, int niveau, Service serviceRattachement, Agent chefDeService) {
        this.lib = lib;
        this.code = code;
        this.description = description;
        this.niveau = niveau;
        this.serviceRattachement = serviceRattachement;
        this.chefDeService = chefDeService;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Service getServiceRattachement() {
        return serviceRattachement;
    }

    public void setServiceRattachement(Service serviceRattachement) {
        this.serviceRattachement = serviceRattachement;
    }

    public Agent getChefDeService() {
        return chefDeService;
    }

    public void setChefDeService(Agent chefDeService) {
        this.chefDeService = chefDeService;
    }
}
