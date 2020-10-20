package com.cmu.demandeConge.dao;

import com.cmu.demandeConge.entities.Annee;
import com.cmu.demandeConge.entities.JourFerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface JourFerieRepository extends JpaRepository<JourFerie, Long> {
    public JourFerie findByLib(String lib);
    public JourFerie findByJour(Date jour);
    public JourFerie findByAnnee(Annee annee);
    @Query("select count(j) from JourFerie as j where j.jour between :x and :y")
    public Integer getNbJourFerie(@Param("x") Date dateDebut, @Param("y") Date dateFin);
}
