package com.cmu.demandeConge.dao;

import com.cmu.demandeConge.entities.Agent;
import com.cmu.demandeConge.entities.DemandePermissionAbsence;
import com.cmu.demandeConge.entities.NiveauValidation;
import com.cmu.demandeConge.entities.ValidationDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ValidationDemandeRepository extends JpaRepository<ValidationDemande, Long> {
    public ValidationDemande findByDemandePermissionAbsence(DemandePermissionAbsence demandePermissionAbsence);
    @Query("select vd from ValidationDemande vd where vd.demandePermissionAbsence=:x and vd.validateur is not null order by vd.dateValidation")
    public List<ValidationDemande> listValidationEffectives(@Param("x") DemandePermissionAbsence demandePermissionAbsence);
    @Query("select vd from ValidationDemande vd where vd.niveauValidation.code=:x and not (vd.dateValidation is null)")
    public List<ValidationDemande> listDemAbsValiderParSH(@Param("x")NiveauValidation niveauValidation);
    @Query("select count(vd.id) from ValidationDemande vd where vd.demandePermissionAbsence=:x and vd.validateur=:y")
    public boolean estValidable(@Param("x") DemandePermissionAbsence demandePermissionAbsence, @Param("y") Agent validateur);
    @Query("select vd from ValidationDemande vd where vd.demandePermissionAbsence=:x and not (vd.dateValidation is null) order by vd.dateValidation")
    public ValidationDemande validation(@Param("x") DemandePermissionAbsence demandePermissionAbsence);
}
