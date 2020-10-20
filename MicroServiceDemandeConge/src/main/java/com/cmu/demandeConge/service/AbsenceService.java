package com.cmu.demandeConge.service;

import com.cmu.demandeConge.entities.*;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface AbsenceService {
    public Integer getNbJourFerie(Date dateDebut, Date dateFin);
    public List<SoldeConges> getSynthese(Agent agent);
    public List<DemandePermissionAbsence> getDemandePermissionAbsenceParService(Service service);
    public List<DemandePermissionAbsence> getlistDemAbsAValider();
    public List<DemandePermissionAbsence> getlistDemAbsAValider(Agent agent);
    public List<DemandePermissionAbsence> getlistDemAbsTraitees();
    public ValidationDemande validationAFaire(DemandePermissionAbsence demandePermissionAbsence);
    public List<ValidationDemande> listValidationEffectives(DemandePermissionAbsence demandePermissionAbsence);
    public List<ValidationDemande> listDemAbsValiderParSH();
    public boolean estValidable(DemandePermissionAbsence demandePermissionAbsence, Agent validateur);
    public ValidationDemande validation(DemandePermissionAbsence demandePermissionAbsence);
    public Absence enregistrer(Absence absence);
    public ValidationDemande enregistrer(ValidationDemande validationDemande);
    public List<DemandePermissionAbsence> getlistDemAbsParAgent(Agent agent);
}
