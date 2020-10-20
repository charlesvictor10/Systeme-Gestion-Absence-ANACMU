package com.cmu.demandeConge.dao;

import com.cmu.demandeConge.entities.Agent;
import com.cmu.demandeConge.entities.DemandePermissionAbsence;
import com.cmu.demandeConge.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandePermissionAbsenceRepository extends JpaRepository<DemandePermissionAbsence,Long> {
    @Query("select dpa from DemandePermissionAbsence dpa where dpa.absence.agent.service=:x order by dpa.dateDemande desc")
    public List<DemandePermissionAbsence> listDemandePermissionAbsenceParService(@Param("x") Service service);
    @Query("select dpa from DemandePermissionAbsence dpa where dpa.absence.agent.superviseur=:x and dpa.etatDemande=:y order by dpa.dateDemande desc")
    public List<DemandePermissionAbsence> listDemAbsAValiderParSH(@Param("x") Agent agent, @Param("y") String etatDemande);
    @Query("select dpa from DemandePermissionAbsence dpa where dpa.etatDemande=:x or dpa.etatDemande=:y order by dpa.dateDemande desc")
    public List<DemandePermissionAbsence> listDemAbsTraitees(@Param("x") String etat, @Param("y") String etat1);
    public List<DemandePermissionAbsence> findByEtatDemande(String etatDemande);
    @Query("select dpa from DemandePermissionAbsence dpa where dpa.absence.agent=:x order by dpa.dateDemande desc")
    public List<DemandePermissionAbsence> listDemandePermissionAbsenceParAgent(@Param("x") Agent agent);
}
