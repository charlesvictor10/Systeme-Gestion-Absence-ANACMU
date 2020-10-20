package com.cmu.parametrage.dao;

import com.cmu.parametrage.entities.Annee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeRepository extends JpaRepository<Annee, Long> {
    public Annee findByAnnee(int annee);
}
