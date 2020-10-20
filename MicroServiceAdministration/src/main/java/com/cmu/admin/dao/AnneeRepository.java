package com.cmu.admin.dao;

import com.cmu.admin.entities.Annee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeRepository extends JpaRepository<Annee, Long> {
    public Annee findByAnnee(int annee);
}
