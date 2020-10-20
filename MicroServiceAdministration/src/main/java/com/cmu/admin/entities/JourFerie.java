package com.cmu.admin.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class JourFerie implements Serializable {
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
    @Temporal(TemporalType.DATE)
    @Column(name = "jour", nullable = false, unique = true)
    private Date jour;
    private boolean estRecurrent;
    private boolean estFixe;
    @ManyToOne
    @JoinColumn(name = "annee_id", referencedColumnName = "id", nullable = false)
    private Annee annee;

    public JourFerie() {
    }

    public JourFerie(String lib, Date jour, boolean estRecurrent, boolean estFixe, Annee annee) {
        this.lib = lib;
        this.jour = jour;
        this.estRecurrent = estRecurrent;
        this.estFixe = estFixe;
        this.annee = annee;
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

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public boolean isEstRecurrent() {
        return estRecurrent;
    }

    public void setEstRecurrent(boolean estRecurrent) {
        this.estRecurrent = estRecurrent;
    }

    public boolean isEstFixe() {
        return estFixe;
    }

    public void setEstFixe(boolean estFixe) {
        this.estFixe = estFixe;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }
}
