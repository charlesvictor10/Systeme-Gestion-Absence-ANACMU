package com.cmu.demandeConge.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class EtatAgent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String situation;
    private Date dateSortie;
    private String raisonSortie;
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @OneToOne
    private Agent agent;

    public EtatAgent() {
    }

    public EtatAgent(String situation, Date dateSortie, String raisonSortie) {
        this.situation = situation;
        this.dateSortie = dateSortie;
        this.raisonSortie = raisonSortie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getRaisonSortie() {
        return raisonSortie;
    }

    public void setRaisonSortie(String raisonSortie) {
        this.raisonSortie = raisonSortie;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
