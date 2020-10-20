package com.cmu.demandeConge.dao;

import com.cmu.demandeConge.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("select a.agent, a.dateDebutAbsence, a.dateFinAbsence, a.motif, a.congesNonDeductible, a.nbJourAbsence, a.nbJourOuvrable, a.nbJourDeductible, a.nbJourNonDeductible from Absence a order by a.agent.nom, a.dateDebutAbsence desc")
    public List<Object[]> findAbsenceByAgent();
}
