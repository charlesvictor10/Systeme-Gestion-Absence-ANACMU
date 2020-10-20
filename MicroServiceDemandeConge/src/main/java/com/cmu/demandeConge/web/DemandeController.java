package com.cmu.demandeConge.web;

import com.cmu.demandeConge.Exception.BadRequestException;
import com.cmu.demandeConge.Exception.ResourceNotFoundException;
import com.cmu.demandeConge.config.MailService;
import com.cmu.demandeConge.dao.*;
import com.cmu.demandeConge.entities.*;
import com.cmu.demandeConge.service.AbsenceService;
import com.cmu.demandeConge.service.SoldeCongesService;
import com.cmu.demandeConge.util.JIDate;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

@RestController
public class DemandeController {
    @Autowired
    private AbsenceRepository absenceRepository;
    @Autowired
    private DemandePermissionAbsenceRepository demandePermissionAbsenceRepository;
    @Autowired
    private ValidationDemandeRepository validationDemandeRepository;
    @Autowired
    private NiveauValidationRepository niveauValidationRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private AbsenceService abSrv;
    @Autowired
    private SoldeCongesService scSrv;

    private SoldeConges scAvant = new SoldeConges();
    private SoldeConges scApres = new SoldeConges();
    private SoldeConges scAgent;

    String raison;
    NiveauValidation niveau;
    String etat;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/enregistrerPermAbsNonDeducConge")
    public Absence enregistrerPermAbsNonDeducConge(@Valid @RequestBody Absence absence) {
        // Contrôles des données
        if(JIDate.avantAujourdhui(absence.getDateDebutAbsence())) {
            throw new RuntimeException("La date de début du congès doit être postérieure à la date du jour");
        }

        if(JIDate.avantJour(absence.getDateFinAbsence(), absence.getDateDebutAbsence())) {
            throw new RuntimeException("La date de fin de permission congès doit être postérieure à la date de début");
        }

        if(JIDate.nbJourOuvrable(absence.getDateDebutAbsence(), absence.getDateFinAbsence()) == 0) {
            throw new RuntimeException("La demande correspond à un jour de weekend");
        }

        // Calcul des jours de congés
        ajaxCalculDate(absence.getDateDebutAbsence(),absence.getNbJourNonDeductible());

        // Enregistrement de la demande d'absence
        try {
            NiveauValidation nivValidation;
            String etatDemande = EtatValidationEnum.Encours.toString();
            String typeAbsence = TypeAbsenceEnum.PermAbsNonDeducConge.toString();
            String etatAbsence = EtatAbsenceEnum.Demandee.toString();

            // Information sur la demande d'absence
            absence.setTypeAbsence(typeAbsence);
            absence.setEtatAbsence(etatAbsence);
            absence.setMotif(absence.getCongesNonDeductible().getLib());
            // Sauvegarde de la demande (Absence)
            Absence a = absenceRepository.save(absence);

            // Information sur la demande d'absence
            DemandePermissionAbsence demandePermissionAbsence = new DemandePermissionAbsence();
            demandePermissionAbsence.setAbsence(a);
            demandePermissionAbsence.setEtatDemande(etatDemande);
            demandePermissionAbsence.setDateDemande(JIDate.now());

            // Sauvegarde de la demande (DemandePermissionAbsence)
            DemandePermissionAbsence dpa = demandePermissionAbsenceRepository.save(demandePermissionAbsence);

            // Information sur la demande de validation
            ValidationDemande validDem = new ValidationDemande();
            if(demandePermissionAbsence.getAbsence().getAgent().superviseurEstDG()) {
                nivValidation = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationRH.getId());
            } else {
                nivValidation = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationSH.getId());
            }
            validDem.setEtatValidation(etatDemande);
            validDem.setNiveauValidation(nivValidation);
            validDem.setDemandePermissionAbsence(dpa);

            // Sauvegarde de la demande (ValidationDemande)
            validationDemandeRepository.save(validDem);

            ////////////Création de l'email///////////////////////
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(demandePermissionAbsence.getAbsence().getAgent().getSuperviseur().getEmailProfessionnel());
            mailMessage.setSubject("Nouvelle demande d'absence à valider.");
            mailMessage.setFrom("victorcharleswade@gmail.com");
            mailMessage.setText("");

            ////////////////Envoie de l'email////////////////////
            mailService.send(mailMessage);

        } catch (Exception e){
            throw new RuntimeException("Une erreur est survenue lors de l'enregistrement de la demande");
        }
        return absence;
    }

    @PostMapping("/enregistrerPermAbsDeducConge")
    public Absence enregistrerPerAbsDeducConge(@Valid @RequestBody Absence absence) {
        if(absence.getAgent().getSuperviseur() == null && !absence.getAgent().estDG()) {
            throw new RuntimeException("Vous devez d'abord définir le superviseur de l'agent");
        }

        // Contrôles des données
        if(JIDate.avantAujourdhui(absence.getDateDebutAbsence())) {
            throw new RuntimeException("La date de début du congès doit être postérieure à la date du jour");
        }

        if(JIDate.avantJour(absence.getDateFinAbsence(), absence.getDateDebutAbsence())) {
            throw new RuntimeException("La date de fin de permission congès doit être postérieure à la date de début");
        }

        if(JIDate.nbJourOuvrable(absence.getDateDebutAbsence(),absence.getDateFinAbsence()) == 0) {
            throw new RuntimeException("La demande correspond à un jour de weekend");
        }

        if(scApres.getNbCongesReliquat() < 0) {
            throw new RuntimeException("Le nombre de jour de congés demandé dépasse votre reliquat");
        }

        // Calcul des jours de congés
        ajaxCalculDateNbj(absence.getDateDebutAbsence(),absence.getDateFinAbsence());

        // Enregistrement de la demande d'absence
        try {
            NiveauValidation nivValidation;
            String etatDemande = EtatValidationEnum.Encours.toString();
            String typeAbsence = TypeAbsenceEnum.PermAbsDeducConge.toString();
            String etatAbsence = EtatAbsenceEnum.Demandee.toString();

            // Information sur la demande d'absence
            absence.setTypeAbsence(typeAbsence);
            absence.setEtatAbsence(etatAbsence);
            absence.setEtatAbsence(etatAbsence);
            // Sauvegarde de la demande (Absence)
            Absence a = absenceRepository.save(absence);

            // Information sur la demande d'absence
            DemandePermissionAbsence demandePermissionAbsence = new DemandePermissionAbsence();
            demandePermissionAbsence.setAbsence(a);
            demandePermissionAbsence.setEtatDemande(etatDemande);
            demandePermissionAbsence.setDateDemande(JIDate.now());
            // Sauvegarde de la demande (DemandePermissionAbsence)
            DemandePermissionAbsence dpa = demandePermissionAbsenceRepository.save(demandePermissionAbsence);

            // Information sur la demande de validation
            ValidationDemande valiDem = new ValidationDemande();
            if(absence.getAgent().superviseurEstDG()) {
                nivValidation = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationRH.getId());
            } else {
                nivValidation = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationSH.getId());
            }
            valiDem.setEtatValidation(etatDemande);
            valiDem.setNiveauValidation(nivValidation);
            valiDem.setDemandePermissionAbsence(dpa);

            // Sauvegarde de la demande (ValidationDemande)
            validationDemandeRepository.save(valiDem);

            ////////////Création de l'email///////////////////////
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(demandePermissionAbsence.getAbsence().getAgent().getSuperviseur().getEmailProfessionnel());
            mailMessage.setSubject("Nouvelle demande d'absence à valider.");
            mailMessage.setFrom("victorcharleswade@gmail.com");
            mailMessage.setText("");

            ////////////////Envoie de l'email////////////////////
            mailService.send(mailMessage);
        } catch (Exception e) {
            throw new RuntimeException("Une erreur est survenue lors de l'enregistrement de la demande");
        }
        return absence;
    }

    @GetMapping("/ajaxGetNbJourNonDeductible")
    public Absence ajaxGetNbJourNonDeductible(CongesNonDeductible congesNonDeductible) {
        Absence absence = new Absence();
        absence.setNbJourNonDeductible(congesNonDeductible.getNbJourConges());

        if(JIDate.estVide(absence.getDateDebutAbsence())){
            absence.setDateDebutAbsence(JIDate.dateDuJourSansHeure());
        }

        if(JIDate.estVide(absence.getDateFinAbsence())){
            absence.setDateFinAbsence(JIDate.ajouterJour(absence.getDateDebutAbsence(), absence.getNbJourNonDeductible() - 1));
        }

        ajaxCalculDate(absence.getDateDebutAbsence(),absence.getNbJourNonDeductible());

        return absence;
    }

    @GetMapping("/ajaxCalculDate")
    public Absence ajaxCalculDate(Date dateDebut, int nbJourNonDeductible) {
        Absence absence = new Absence();
        absence.setNbJourNonDeductible(nbJourNonDeductible);
        absence.setDateDebutAbsence(dateDebut);
        Integer nbJourFerie = abSrv.getNbJourFerie(absence.getDateDebutAbsence(),(JIDate.ajouterJour(absence.getDateDebutAbsence(),absence.getNbJourNonDeductible() - 1)));
        absence.setNbJourOuvrable(JIDate.nbJourOuvrable(absence.getDateDebutAbsence(), (JIDate.ajouterJour(absence.getDateDebutAbsence(),absence.getNbJourNonDeductible() - 1))) - nbJourFerie);
        if(absence.getNbJourOuvrable() == absence.getNbJourNonDeductible()){
            absence.setDateFinAbsence(JIDate.ajouterJour(absence.getDateDebutAbsence(),absence.getNbJourNonDeductible() - 1));
            absence.setNbJourAbsence((int) JIDate.nbJour(absence.getDateDebutAbsence(),absence.getDateFinAbsence()) + 1);
        } else {
            nbJourNonDeductible = (int) ((absence.getNbJourOuvrable() + (absence.getNbJourNonDeductible() - absence.getNbJourOuvrable())) + (absence.getNbJourNonDeductible() - absence.getNbJourOuvrable()));
            absence.setDateFinAbsence(JIDate.ajouterJour(absence.getDateDebutAbsence(),nbJourNonDeductible - 1));
            absence.setNbJourAbsence((int) JIDate.nbJour(absence.getDateDebutAbsence(),absence.getDateFinAbsence()) + 1);
        }

        return absence;
    }

    @GetMapping("/ajaxCalculDateNbj")
    public Object[] ajaxCalculDateNbj(Date dateDebut, Date dateFin){
        Absence abs = new Absence();
        Integer nbJourFerie = abSrv.getNbJourFerie(dateDebut,dateFin);
        abs.setNbJourAbsence((int) JIDate.nbJour(dateDebut,dateFin) + 1);
        abs.setNbJourOuvrable(JIDate.nbJourOuvrable(dateDebut,dateFin) - nbJourFerie);
        abs.setNbJourDeductible(JIDate.nbJourOuvrable(dateDebut,dateFin) - nbJourFerie);
        scApres.setNbCongesEchus(scAvant.getNbCongesEchus() - abs.getNbJourDeductible());
        scApres.setNbJourPris(scAvant.getNbJourPris() + abs.getNbJourDeductible());
        scApres.setNbCongesReliquat(scApres.getNbCongesAcquis() - scApres.getNbJourPris());
        Object[] scabs = {abs,scApres};
        System.out.println(scApres);
        return scabs;
    }

    @GetMapping("/ajaxGetSoldeAgent")
    public SoldeConges ajaxGetSoldeAgent(Agent agent){
        SoldeConges soldeConges = scSrv.getSoldeConges(agent);
        scAvant.setValeur(soldeConges);
        scApres.setValeur(soldeConges);
        return soldeConges;
    }

    public SoldeConges getScAvant() {
        return scAvant;
    }

    public void setScAvant(SoldeConges scAvant) {
        this.scAvant = scAvant;
    }

    public SoldeConges getScApres() {
        return scApres;
    }

    public void setScApres(SoldeConges scApres) {
        this.scApres = scApres;
    }

    @GetMapping(value = "/permissions")
    public List<DemandePermissionAbsence> getDemandePermissionAbsence() {
        return demandePermissionAbsenceRepository.findAll();
    }

    @GetMapping(value = "/permissions/{id}")
    public ResponseEntity<DemandePermissionAbsence> getDemandePermissionAbsenceById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        DemandePermissionAbsence demandePermissionAbsence = demandePermissionAbsenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande permission absence introuvable pour cet ", "id", id));
        return ResponseEntity.ok().body(demandePermissionAbsence);
    }

    @PutMapping(value = "/permissions/{id}")
    public ResponseEntity<DemandePermissionAbsence> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Absence absence, DemandePermissionAbsence demandePermissionAbsence) throws ResourceNotFoundException {
        Absence a = absenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande Permission Absence introuvable pour cet ", "ID:", id));
        absence.setUpdateDate(new Date());
        Absence updateAbsence = absenceRepository.save(a);

        demandePermissionAbsence.setUpdateDate(new Date());
        demandePermissionAbsence.setAbsence(updateAbsence);
        demandePermissionAbsence.setId(updateAbsence.getId());
        final DemandePermissionAbsence updateDemandePermissionAbsence = demandePermissionAbsenceRepository.save(demandePermissionAbsence);
        return ResponseEntity.ok(updateDemandePermissionAbsence);
    }

    @GetMapping(value = "/permissionsParService")
    public List<DemandePermissionAbsence> getListDemandesParService(Service service){
        return abSrv.getDemandePermissionAbsenceParService(service);
    }

    @GetMapping(value = "/permissionsAValider")
    public List<DemandePermissionAbsence> getlistDemAbsAValider(){
        return abSrv.getlistDemAbsAValider();
    }

    @GetMapping(value = "/permissionsAValiderParSH")
    public List<DemandePermissionAbsence> getlistDemAbsAValider(Agent agent){
        return abSrv.getlistDemAbsAValider(agent);
    }

    @GetMapping(value = "/permissionsTraitees")
    public List<DemandePermissionAbsence> getlistDemAbsTraitees(){
        return abSrv.getlistDemAbsTraitees();
    }

    @GetMapping(value = "/permissionsParAgent")
    public List<DemandePermissionAbsence> getlistDemAbsParAgent(Agent agent){
        return abSrv.getlistDemAbsParAgent(agent);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/niveauValidations")
    public List<NiveauValidation> getNiveauValidation() {
        return niveauValidationRepository.findAll();
    }

    @PostMapping(value = "/saveNiveauValidation")
    public NiveauValidation saveNiveauValidation(@Valid @RequestBody NiveauValidation niveauValidation) {
        NiveauValidation nv;
        try {
            nv = niveauValidationRepository.save(niveauValidation);
        }catch (Exception e){
            throw new BadRequestException("Impossible d'enregistrer le niveau de validation.", e);
        }
        return nv;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping(value = "/accepter")
    public void accepter() {
        DemandePermissionAbsence demAbs = new DemandePermissionAbsence();
        Absence abs = demAbs.getAbsence();
        String nivValidation;

        try {
            nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode();

            if(nivValidation.equals(NiveauValidationDemandeEnum.ValidationSH.toString())){
                niveau = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationRH.getId());
                etat = EtatValidationEnum.Encours.toString();
            }

            if(nivValidation.equals(NiveauValidationDemandeEnum.ValidationRH.toString())){
                niveau = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationDG.getId());
                etat = EtatValidationEnum.Encours.toString();
            }

            if(nivValidation.equals(NiveauValidationDemandeEnum.ValidationDG.toString())){
                niveau = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationDG.getId());
                etat = EtatValidationEnum.Acceptee.toString();
                demAbs.setDateAcceptation(JIDate.now());

                // Mise à jour du solde de congés
                abs.setEtatAbsence(EtatAbsenceEnum.Acceptee.toString());
                abs = abSrv.enregistrer(abs);

                if(abs.getTypeAbsence().equals(TypeAbsenceEnum.PermAbsDeducConge.toString())){
                    SoldeConges sc = scSrv.getSoldeConges(abs.getAgent());
                    sc.setNbCongesEchus(sc.getNbCongesEchus() - abs.getNbJourDeductible());
                    sc.setNbJourPris(sc.getNbJourPris() + abs.getNbJourDeductible());
                    sc.setNbCongesReliquat(sc.getNbCongesAcquis() - sc.getNbJourPris());
                    sc = scSrv.enregistrer(sc);
                }
            }
            validation(demAbs);

            abs = abSrv.enregistrer(abs);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/refuser")
    public void refuser(){
        DemandePermissionAbsence demAbs = new DemandePermissionAbsence();
        String nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode();

        if(nivValidation.equals(NiveauValidationDemandeEnum.ValidationSH.toString())){
            niveau = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationSH.getId());
            etat = EtatValidationEnum.Refusee.toString();
            demAbs.setEtatDemande(EtatValidationEnum.Refusee.toString());
        }

        if(nivValidation.equals(NiveauValidationDemandeEnum.ValidationRH.toString())){
            niveau = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationRH.getId());
            etat = EtatValidationEnum.Refusee.toString();
            demAbs.setEtatDemande(EtatValidationEnum.Refusee.toString());
        }

        if(nivValidation.equals(NiveauValidationDemandeEnum.ValidationDG.toString())){
            niveau = niveauValidationRepository.getOne(NiveauValidationDemandeEnum.ValidationDG.getId());
            etat = EtatValidationEnum.Refusee.toString();
            demAbs.setEtatDemande(EtatValidationEnum.Refusee.toString());
        }
        validation(demAbs);

        // Mise à jour du solde de congés
        Absence abs = demAbs.getAbsence();
        abs.setEtatAbsence(EtatValidationEnum.Refusee.toString());
        abs = abSrv.enregistrer(abs);
    }

    public void validation(DemandePermissionAbsence demaAbs){
        ValidationDemande vd = new ValidationDemande();
        vd.setDemandePermissionAbsence(demaAbs);
        vd.setObservations(raison);
        vd.setNiveauValidation(niveau);
        vd.setEtatValidation(etat);
        vd.setValidateur(demaAbs.getAbsence().getAgent().getSuperviseur());

        vd = abSrv.enregistrer(vd);

        ////////////Création de l'email///////////////////////
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(demaAbs.getAbsence().getAgent().getEmailProfessionnel());
        mailMessage.setSubject("Etat de votre demande.");
        mailMessage.setFrom("victorcharleswade@gmail.com");
        mailMessage.setText("Votre demande d'absence du " +demaAbs.getDateDemande()+ "est:" +vd.getEtatValidation()+ "pour les raisons suivantes:" +vd.getObservations());

        ////////////////Envoie de l'email////////////////////
        mailService.send(mailMessage);
    }

    public SoldeConges getScAgent() {
        return scAgent;
    }

    public void setScAgent(SoldeConges scAgent) {
        this.scAgent = scAgent;
    }

    public boolean getDepassementNbJourEchus() {
        DemandePermissionAbsence demAbs = new DemandePermissionAbsence();
        Absence abs;

        if(demAbs != null && demAbs.getAbsence() != null) {
            abs = demAbs.getAbsence();
            return (scAgent.getNbCongesEchus() < abs.getNbJourDeductible());
        }
        return false;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/exportPdf/{id}")
    public void printPDF(HttpServletResponse response, @PathVariable(value = "id") Long id) throws SQLException {
        Absence abs = absenceRepository.getOne(id);
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sga2_bd","cmubourbi","Sine4+koumbA623");
        if (abs.getTypeAbsence().equals(TypeAbsenceEnum.PermAbsDeducConge.toString())) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", abs.getId().intValue());
            String filename = "reportAbs"+abs.getId().toString().trim()+".pdf";
            String jasperPath = "C:\\Users\\victor charles wade\\IdeaProjects\\MicroServiceDemandeConge\\src\\main\\resources\\report\\reportDmdAbs.jasper";
            this.PDF(response, conn, params, jasperPath, filename);
        }

        if (abs.getTypeAbsence().equals(TypeAbsenceEnum.PermAbsNonDeducConge.toString())) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", abs.getId().intValue());
            String filename = "reportAbsNonDeduct"+abs.getId().toString().trim()+".pdf";
            String jasperPath = "C:\\Users\\victor charles wade\\IdeaProjects\\MicroServiceDemandeConge\\src\\main\\resources\\report\\reportDmdAbsND.jasper";
            this.PDF(response, conn, params, jasperPath, filename);
        }
    }

    private void PDF(HttpServletResponse response, Connection conn, Map<String, Object> params, String jasperPath, String filename) {
        File file = new File(jasperPath);
        try {
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, conn);
            response.addHeader("Content-disposition", "attachment; filename="+filename);
            JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\victor charles wade\\Downloads\\"+filename);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
