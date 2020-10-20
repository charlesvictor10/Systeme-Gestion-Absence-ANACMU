package com.cmu.demandeConge.service;

import com.cmu.demandeConge.dao.*;
import com.cmu.demandeConge.entities.*;
import com.cmu.demandeConge.util.JIDate;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Service
public class AbsenceServiceImpl implements AbsenceService {
    @Autowired
    private JourFerieRepository jourFerieRepository;
    @Autowired
    private AbsenceRepository absenceRepository;
    @Autowired
    private DemandePermissionAbsenceRepository demandePermissionAbsenceRepository;
    @Autowired
    private ValidationDemandeRepository validationDemandeRepository;
    @Autowired
    private NiveauValidationRepository niveauValidationRepository;
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer getNbJourFerie(Date dateDebut, Date dateFin) {
        if(JIDate.estVide(dateDebut) || JIDate.estVide(dateFin)) {
            return 0;
        }
        Date debut = JIDate.dateSansHeure(dateDebut);
        Date fin = JIDate.dateSansHeure(dateFin);
        return jourFerieRepository.getNbJourFerie(debut,fin);
    }

    @Override
    public List<SoldeConges> getSynthese(Agent agent) {
        return null;
    }

    @Override
    public List<DemandePermissionAbsence> getDemandePermissionAbsenceParService(com.cmu.demandeConge.entities.Service service) {
        return demandePermissionAbsenceRepository.listDemandePermissionAbsenceParService(service);
    }

    @Override
    public List<DemandePermissionAbsence> getlistDemAbsAValider() {
        String etat = EtatValidationEnum.Encours.toString();
        return demandePermissionAbsenceRepository.findByEtatDemande(etat);
    }

    @Override
    public List<DemandePermissionAbsence> getlistDemAbsAValider(Agent agent) {
        String etat = EtatValidationEnum.Encours.toString();
        return demandePermissionAbsenceRepository.listDemAbsAValiderParSH(agent,etat);
    }

    @Override
    public List<DemandePermissionAbsence> getlistDemAbsTraitees() {
        String etat = EtatValidationEnum.Acceptee.toString();
        String etat1 = EtatValidationEnum.Refusee.toString();
        return demandePermissionAbsenceRepository.listDemAbsTraitees(etat,etat1);
    }

    @Override
    public ValidationDemande validationAFaire(DemandePermissionAbsence demandePermissionAbsence) {
        return validationDemandeRepository.findByDemandePermissionAbsence(demandePermissionAbsence);
    }

    @Override
    public List<ValidationDemande> listValidationEffectives(DemandePermissionAbsence demandePermissionAbsence) {
        if (demandePermissionAbsence == null) {
            return null;
        }

        return validationDemandeRepository.listValidationEffectives(demandePermissionAbsence);
    }

    @Override
    public List<ValidationDemande> listDemAbsValiderParSH() {
        NiveauValidation nv = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationRH.getId());
        return validationDemandeRepository.listDemAbsValiderParSH(nv);
    }

    @Override
    public boolean estValidable(DemandePermissionAbsence demandePermissionAbsence, Agent validateur) {
        long nbValidation = 0;

        try {
            return validationDemandeRepository.estValidable(demandePermissionAbsence,validateur);
        } catch (Exception e) {
            return (nbValidation == 0);
        }
    }

    @Override
    public ValidationDemande validation(DemandePermissionAbsence demandePermissionAbsence) {
        return validationDemandeRepository.validation(demandePermissionAbsence);
    }

    @Override
    public Absence enregistrer(Absence absence) {
        return absenceRepository.save(absence);
    }

    @Override
    public ValidationDemande enregistrer(ValidationDemande validationDemande) {
        return validationDemandeRepository.save(validationDemande);
    }

    @Override
    public List<DemandePermissionAbsence> getlistDemAbsParAgent(Agent agent) {
        return demandePermissionAbsenceRepository.listDemandePermissionAbsenceParAgent(agent);
    }
}
