package com.cmu.admin.dao;

import com.cmu.admin.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("select a.agent, sum(a.nbJourAbsence) as nja, sum(a.nbJourOuvrable) as njo, sum(a.nbJourDeductible) as njd, sum(a.nbJourNonDeductible) as njnd from Absence as a group by a.agent.id")
    public List<Object[]> findAgentBySumOfNbJourAbsenceAndNbJourOuvrableAndNbJourDeductibleAndNbJourNonDeductible();
    @Query("select a.agent, a.dateDebutAbsence, a.dateFinAbsence, a.motif, a.congesNonDeductible, a.nbJourAbsence, a.nbJourOuvrable, a.nbJourDeductible, a.nbJourNonDeductible from Absence a order by a.agent.nom, a.dateDebutAbsence desc")
    public List<Object[]> findAbsenceByAgent();
}
