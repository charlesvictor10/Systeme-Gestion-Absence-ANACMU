package com.cmu.admin.dao;

import com.cmu.admin.entities.Annee;
import com.cmu.admin.entities.JourFerie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface JourFerieRepository extends JpaRepository<JourFerie, Long> {
    public JourFerie findByLib(String lib);

    public JourFerie findByJour(Date jour);

    public JourFerie findByAnnee(Annee annee);
}
