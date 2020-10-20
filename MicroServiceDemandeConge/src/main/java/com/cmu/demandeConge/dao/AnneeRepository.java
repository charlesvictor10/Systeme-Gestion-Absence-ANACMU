package com.cmu.demandeConge.dao;

import com.cmu.demandeConge.entities.Annee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeRepository extends JpaRepository<Annee, Long> {
    public Annee findByAnnee(int annee);
}
