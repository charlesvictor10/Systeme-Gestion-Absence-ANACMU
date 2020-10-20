package com.cmu.admin.dao;

import com.cmu.admin.entities.Agent;
import com.cmu.admin.entities.DemandePermissionAbsence;
import com.cmu.admin.entities.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandePermissionAbsenceRepository extends JpaRepository<DemandePermissionAbsence,Long> {
    @Query("select dpa from DemandePermissionAbsence dpa where dpa.absence.agent=:x order by dpa.dateDemande desc")
    public List<DemandePermissionAbsence> listDemandePermissionAbsenceParAgent(@Param("x") Agent agent);
    @Query("select dpa from DemandePermissionAbsence dpa where dpa.absence.etat=:x order by dpa.dateDemande desc")
    public List<DemandePermissionAbsence> listDemandePermissionAbsenceParEtat(@Param("x")Etat etat);
}
