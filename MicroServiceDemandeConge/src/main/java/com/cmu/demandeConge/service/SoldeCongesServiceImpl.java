package com.cmu.demandeConge.service;

import com.cmu.demandeConge.dao.SoldeCongesRepository;
import com.cmu.demandeConge.entities.Agent;
import com.cmu.demandeConge.entities.SoldeConges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
public class SoldeCongesServiceImpl implements SoldeCongesService {
    private EntityManager em;
    @Autowired
    private SoldeCongesRepository soldeCongesRepository;

    public SoldeCongesServiceImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void executerPSCalculSolde() {
        StoredProcedureQuery psRecalculerSoldeConges = em.createStoredProcedureQuery("ps_recalculer_solde_conges");
        psRecalculerSoldeConges.execute();
    }

    @Override
    public SoldeConges getSoldeConges(Agent agent) {
        return soldeCongesRepository.findByAgent(agent);
    }

    @Override
    public List<SoldeConges> listSoldeConges() {
        return soldeCongesRepository.findAll();
    }

    @Override
    public List<SoldeConges> soldeCongesList(Agent agent) {
        return null;
    }

    @Override
    public SoldeConges enregistrer(SoldeConges soldeConges) {
        return soldeCongesRepository.save(soldeConges);
    }
}
