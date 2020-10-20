package com.cmu.admin.dao;

import com.cmu.admin.entities.DemandePermissionAbsence;
import com.cmu.admin.entities.ValidationDemande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValidationDemandeRepository extends JpaRepository<ValidationDemande, Long> {
    public List<ValidationDemande> findByDemandePermissionAbsence(DemandePermissionAbsence demandePermissionAbsence);
}
