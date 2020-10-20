package com.cmu.demandeConge.web;

import com.cmu.demandeConge.Exception.ResourceNotFoundException;
import com.cmu.demandeConge.dao.DemandePermissionAbsenceRepository;
import com.cmu.demandeConge.dao.SoldeCongesRepository;
import com.cmu.demandeConge.entities.*;
import com.cmu.demandeConge.service.AbsenceService;
import com.cmu.demandeConge.service.SoldeCongesService;
import com.cmu.demandeConge.util.JIDate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
public class EtatController {
    @Autowired
    private DemandePermissionAbsenceRepository demandePermissionAbsenceRepository;
    @Autowired
    private SoldeCongesRepository soldeCongesRepository;
    @Autowired
    private SoldeCongesService service;
    @Autowired
    private AbsenceService abSrv;

    private SoldeConges scAvant = new SoldeConges();
    private SoldeConges scApres = new SoldeConges();
    private SoldeConges sc;
    private Absence absence;

   /* @PostMapping(value = "addAbsence")
    public Absence addAbsence(Agent agent) {
        sc = service.getSoldeConges(agent);
        scAvant = new SoldeConges();
        scAvant.setValeur(sc);
        absence = new Absence();
        return absence;
    }*/

    @GetMapping("/getNbJourNonDeductible")
    public Absence getNbJourNonDeductible(CongesNonDeductible congesNonDeductible) {
        Absence absence = new Absence();
        absence.setNbJourNonDeductible(congesNonDeductible.getNbJourConges());

        if(JIDate.estVide(absence.getDateDebutAbsence())){
            absence.setDateDebutAbsence(JIDate.dateDuJourSansHeure());
        }

        if(JIDate.estVide(absence.getDateFinAbsence())){
            absence.setDateFinAbsence(JIDate.ajouterJour(absence.getDateDebutAbsence(), absence.getNbJourNonDeductible() - 1));
        }

        calculDate(absence.getDateDebutAbsence(),absence.getNbJourNonDeductible());

        return absence;
    }

    @GetMapping("/calculDateNbj")
    public Object[] calculDateNbj(Date dateDebut, Date dateFin){
        Absence abs = new Absence();
        Integer nbJourFerie = abSrv.getNbJourFerie(dateDebut,dateFin);
        abs.setNbJourAbsence((int) JIDate.nbJour(dateDebut,dateFin) + 1);
        abs.setNbJourOuvrable(JIDate.nbJourOuvrable(dateDebut,dateFin) - nbJourFerie);
        abs.setNbJourDeductible(JIDate.nbJourOuvrable(dateDebut,dateFin) - nbJourFerie);
        scApres.setNbCongesEchus(scAvant.getNbCongesEchus() - abs.getNbJourDeductible());
        scApres.setNbJourPris(scAvant.getNbJourPris() + abs.getNbJourDeductible());
        scApres.setNbCongesReliquat(scApres.getNbCongesAcquis() - scApres.getNbJourPris());
        Object[] scabs = {abs,scApres};
        return scabs;
    }

    @GetMapping("/calculDate")
    public Absence calculDate(Date dateDebut,int nbJourNonDeductible) {
        Absence absence = new Absence();
        absence.setDateFinAbsence(JIDate.ajouterJour(dateDebut, nbJourNonDeductible - 1));
        Integer nbJourFerie = abSrv.getNbJourFerie(dateDebut, absence.getDateFinAbsence());
        absence.setNbJourAbsence((int) JIDate.nbJour(dateDebut,absence.getDateFinAbsence()) + 1);
        if(JIDate.estVide(absence.getDateFinAbsence())){
            JIDate.ajouterJour(dateDebut, nbJourNonDeductible - 1);
        }
        absence.setNbJourOuvrable(JIDate.nbJourOuvrable(dateDebut, absence.getDateFinAbsence()) - nbJourFerie);

        return absence;
    }

    @PostMapping(value = "/enregistrerAbsence")
    public Absence enregistrerAbsence(Absence absence) {
        // Contrôles des données
        if(JIDate.apresAujourdhui(absence.getDateDebutAbsence()) || JIDate.apresAujourdhui(absence.getDateFinAbsence())) {
            throw new RuntimeException("Vous ne pouvez enregistrer que des absences passées");
        }

        if(JIDate.avantJour(absence.getDateFinAbsence(), absence.getDateDebutAbsence())) {
            throw new RuntimeException("La date de fin de permission congès doit être postérieure à la date de début");
        }

        // Calcul des jours de congés
        calculDateNbj(absence.getDateDebutAbsence(),absence.getDateFinAbsence());

        // Enregistrement de la demande d'absence
        try{
            String typeAbsence = TypeAbsenceEnum.PermAbsDeducConge.toString();
            String etatAbsence = EtatAbsenceEnum.Effective.toString();

            // Information sur la demande d'absence
            absence.setTypeAbsence(typeAbsence);
            absence.setEtatAbsence(etatAbsence);

            // Sauvegarde de l'absence
            absence = abSrv.enregistrer(absence);

            // Sauvegarde de l'absence
            sc = service.enregistrer(sc);
        } catch (Exception e) {
            throw new RuntimeException("Une erreur est survenue lors de l'enregistrement de la demande");
        }
        return absence;
    }

    @PostMapping(value = "/saveSoldeConge")
    public SoldeConges enregistrer(@Valid @RequestBody SoldeConges soldeConges) {
        try {
            soldeConges.setNbCongesReliquat(soldeConges.getNbCongesAcquis() - soldeConges.getNbJourPris());
            soldeConges.setDateDernierCalcul(JIDate.now());
            soldeConges = service.enregistrer(soldeConges);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du nouveau solde");
        }
        return soldeConges;
    }

    @GetMapping("/soldeConges/{id}")
    public ResponseEntity<?> getSoldeConges(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        SoldeConges soldeConges = soldeCongesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solde introuvable pour cet ", "id", id));
        return ResponseEntity.ok().body(soldeConges);
    }

    @GetMapping(value = "/listeEtatAbsenceAgent")
    public List<DemandePermissionAbsence> getAbsenceByAgent(){
        return demandePermissionAbsenceRepository.findAll();
    }

    @GetMapping(value = "/listeSoldeCongesAgent")
    public List<SoldeConges> getSoldeCongesAgents(){
        return service.listSoldeConges();
    }

    @GetMapping(value = "/soldeCongesParAgent")
    public SoldeConges getSoldeCongesParAgent(Agent agent){
        SoldeConges soldeConges = service.getSoldeConges(agent);
        scAvant.setValeur(soldeConges);
        scApres.setValeur(soldeConges);
        return soldeConges;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/exportExcel")
    public void exportSolde(HttpServletResponse response) throws IOException {
        String filename = "soldeconges.xls";

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Solde des congés des agents");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        Cell ordre = headerRow.createCell(0);
        ordre.setCellValue("ORDRE");
        ordre.setCellStyle(headerCellStyle);
        Cell matricule = headerRow.createCell(1);
        matricule.setCellValue("MATRICULE");
        matricule.setCellStyle(headerCellStyle);
        Cell prenom = headerRow.createCell(2);
        prenom.setCellValue("PRENOM");
        prenom.setCellStyle(headerCellStyle);
        Cell nom = headerRow.createCell(3);
        nom.setCellValue("NOM");
        nom.setCellStyle(headerCellStyle);
        Cell genre = headerRow.createCell(4);
        genre.setCellValue("GENRE");
        genre.setCellStyle(headerCellStyle);
        Cell bureau = headerRow.createCell(5);
        bureau.setCellValue("BUREAU");
        bureau.setCellStyle(headerCellStyle);
        Cell service = headerRow.createCell(6);
        service.setCellValue("SERVICE");
        service.setCellStyle(headerCellStyle);
        Cell fonction = headerRow.createCell(7);
        fonction.setCellValue("FONCTION");
        fonction.setCellStyle(headerCellStyle);
        Cell dateDebut = headerRow.createCell(8);
        dateDebut.setCellValue("DATE PRISE SERVICE");
        dateDebut.setCellStyle(headerCellStyle);
        Cell emailPro = headerRow.createCell(9);
        emailPro.setCellValue("EMAIL PRO");
        emailPro.setCellStyle(headerCellStyle);
        Cell teleGov = headerRow.createCell(10);
        teleGov.setCellValue("TEL eGOV");
        teleGov.setCellStyle(headerCellStyle);
        Cell telBureau = headerRow.createCell(11);
        telBureau.setCellValue("TEL BUREAU");
        telBureau.setCellStyle(headerCellStyle);
        Cell numPoste = headerRow.createCell(12);
        numPoste.setCellValue("N° POSTE");
        numPoste.setCellStyle(headerCellStyle);
        Cell telPers = headerRow.createCell(13);
        telPers.setCellValue("TEL PERSO");
        telPers.setCellStyle(headerCellStyle);
        Cell emailPers = headerRow.createCell(14);
        emailPers.setCellValue("EMAIL PERSO");
        emailPers.setCellStyle(headerCellStyle);
        Cell adresse = headerRow.createCell(15);
        adresse.setCellValue("ADRESSE");
        adresse.setCellStyle(headerCellStyle);
        Cell numPiece = headerRow.createCell(16);
        numPiece.setCellValue("N° PIECE");
        numPiece.setCellStyle(headerCellStyle);
        Cell acquis = headerRow.createCell(17);
        acquis.setCellValue("ACQUIS");
        acquis.setCellStyle(headerCellStyle);
        Cell pris = headerRow.createCell(18);
        pris.setCellValue("PRIS");
        pris.setCellStyle(headerCellStyle);
        Cell reliquat = headerRow.createCell(19);
        reliquat.setCellValue("RELIQUAT");
        reliquat.setCellStyle(headerCellStyle);
        Cell echus = headerRow.createCell(20);
        echus.setCellValue("ECHUS");
        echus.setCellStyle(headerCellStyle);
        Cell courrus = headerRow.createCell(21);
        courrus.setCellValue("COURS");
        courrus.setCellStyle(headerCellStyle);
        Cell supp = headerRow.createCell(22);
        supp.setCellValue("SUPPLEMENTAIRE");
        supp.setCellStyle(headerCellStyle);

        List<SoldeConges> lSc = soldeCongesRepository.findAll();

        int rowNum = 1;
        for (SoldeConges sc : lSc) {
            Row row = sheet.createRow(rowNum++);
            row.createCell((short)0).setCellValue(rowNum);
            row.createCell((short)1).setCellValue(sc.getAgent().getMatricule());
            row.createCell((short)2).setCellValue(sc.getAgent().getPrenom());
            row.createCell((short)3).setCellValue(sc.getAgent().getNom());
            row.createCell((short)4).setCellValue(sc.getAgent().getGenre());
            row.createCell((short)5).setCellValue(sc.getAgent().getEntite().getLib());
            row.createCell((short)6).setCellValue(sc.getAgent().getService().getLib());
            row.createCell((short)7).setCellValue(sc.getAgent().getFonction().getLib());
            row.createCell((short)8).setCellValue(sc.getAgent().getDatePriseFonction());
            row.createCell((short)9).setCellValue(sc.getAgent().getEmailProfessionnel());
            row.createCell((short)10).setCellValue(sc.getAgent().getTelProfessionnel());
            row.createCell((short)11).setCellValue(sc.getAgent().getTelBureau());
            row.createCell((short)12).setCellValue(sc.getAgent().getNumeroPoste());
            row.createCell((short)13).setCellValue(sc.getAgent().getTel1());
            row.createCell((short)14).setCellValue(sc.getAgent().getEmail());
            row.createCell((short)15).setCellValue(sc.getAgent().getAdresse1()+ " "+ sc.getAgent().getAdresse2()+ " "+ sc.getAgent().getAdresse3());
            row.createCell((short)16).setCellValue(sc.getAgent().getCni());
            row.createCell((short)17).setCellValue(sc.getNbCongesAcquis());
            row.createCell((short)18).setCellValue(sc.getNbJourPris());
            row.createCell((short)19).setCellValue(sc.getNbCongesReliquat());
            row.createCell((short)20).setCellValue(sc.getNbCongesEchus());
            row.createCell((short)21).setCellValue(sc.getNbCongesEncours());
            row.createCell((short)22).setCellValue(sc.getNbCongesSupplementaire());
        }

        //The response is stored in an outputstream
        OutputStream out = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        byte[] byteArray = ((HSSFWorkbook)workbook).getBytes();
        out.write(byteArray);
        out.flush();
        out.close();

        //Wrote this one just for testing if the file is already corrupt here. --> It's fine.
        FileOutputStream fos = new FileOutputStream("C:\\Users\\victor charles wade\\Downloads\\"+filename);
        fos.write(byteArray);
        fos.flush();
        fos.close();
    }

    @GetMapping(value = "/calculSolde")
    public ResponseEntity<?> calculerSolde() {
        try {
            service.executerPSCalculSolde();
            return ResponseEntity.ok().body("Les soldes ont été calculés avec succès");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du lancement de la procédure stockée");
        }
    }
}
