package com.cmu.parametrage.dao;

import com.cmu.parametrage.entities.Annee;
import com.cmu.parametrage.entities.JourFerie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface JourFerieRepository extends JpaRepository<JourFerie, Long> {
    public JourFerie findByLib(String lib);
    public JourFerie findByJour(Date jour);
}
