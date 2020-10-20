package com.cmu.demandeConge.dao;

import com.cmu.demandeConge.entities.Agent;
import com.cmu.demandeConge.entities.SoldeConges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoldeCongesRepository extends JpaRepository<SoldeConges,Long> {
    public SoldeConges findByAgent(Agent agent);
}
