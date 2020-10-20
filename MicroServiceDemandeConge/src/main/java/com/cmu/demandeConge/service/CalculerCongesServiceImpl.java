package com.cmu.demandeConge.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

@Service
public class CalculerCongesServiceImpl implements CalculerCongesService {
    private EntityManager em;

    public CalculerCongesServiceImpl(EntityManager em) {
        this.em = em;
    }

    @Scheduled(cron = "* * 1 * * ?")
    public void calculerSoldeConges() {
        StoredProcedureQuery psRecalculerSoldeConges = em.createStoredProcedureQuery("ps_recalculer_solde_conges");
        psRecalculerSoldeConges.execute();
    }
}
