package com.cmu.demandeConge.service;

import com.cmu.demandeConge.entities.Agent;
import com.cmu.demandeConge.entities.SoldeConges;

import java.util.List;

public interface SoldeCongesService {
    public void executerPSCalculSolde();
    public SoldeConges getSoldeConges(Agent agent);
    public List<SoldeConges> listSoldeConges();
    public List<SoldeConges> soldeCongesList(Agent agent);
    public SoldeConges enregistrer(SoldeConges  soldeConges);
}
