package com.cmu.parametrage.web;

import com.cmu.parametrage.Exception.BadRequestException;
import com.cmu.parametrage.Exception.ResourceNotFoundException;
import com.cmu.parametrage.dao.*;
import com.cmu.parametrage.entities.*;
import com.cmu.parametrage.util.JIDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ParametreController {
    @Autowired
    private AnneeRepository anneeRepository;
    @Autowired
    private JourFerieRepository jourFerieRepository;
    @Autowired
    private CongesNonDeductibleRepository congesNonDeductibleRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private StatutRepository statutRepository;
    @Autowired
    private CiviliteRepository civiliteRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private EntiteRepository entiteRepository;

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/annees")
    public List<Annee> getAnnees() {
        return anneeRepository.findAll();
    }

    @PostMapping(value = "/saveAnnee")
    public Annee save(@Valid @RequestBody Annee annee) {
        Annee a = anneeRepository.findByAnnee(annee.getAnnee());
        if (a != null) {
            throw new BadRequestException("L'année est déjà définie.");
        } else {
            return anneeRepository.save(annee);
        }
    }

    @GetMapping("/annees/{id}")
    public ResponseEntity<Annee> getAnneeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Annee annee = anneeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annee introuvable pour cet ", "id", id));
        return ResponseEntity.ok().body(annee);
    }

    @PutMapping("/annees/{id}")
    public ResponseEntity<Annee> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Annee anneeDetails) throws ResourceNotFoundException {
        Annee annee = anneeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annee introuvable pour cet ", "ID:", id));

        annee.setUpdateDate(JIDate.now());
        annee.setAnnee(anneeDetails.getAnnee());
        annee.setEstCourante(anneeDetails.isEstCourante());
        annee.setEstCachee(anneeDetails.isEstCachee());
        final Annee updatedAnnee = anneeRepository.save(annee);
        return ResponseEntity.ok(updatedAnnee);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/services")
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    @PostMapping("/saveService")
    public Service save(@Valid @RequestBody Service service) {
        if (serviceRepository.existsByCode(service.getCode())) {
            throw new BadRequestException("Le service est déjà défini");
        }

        return serviceRepository.save(service);
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service introuvable pour cet ", "id", id));
        return ResponseEntity.ok().body(service);
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<Service> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Service serviceDetails) throws ResourceNotFoundException {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service introuvable pour cet ", "ID:", id));

        service.setUpdateDate(JIDate.now());
        service.setCode(serviceDetails.getCode());
        service.setLib(serviceDetails.getLib());
        service.setNiveau(serviceDetails.getNiveau());
        service.setDescription(serviceDetails.getDescription());
        service.setServiceRattachement(serviceDetails.getServiceRattachement());
        final Service updatedService = serviceRepository.save(service);
        return ResponseEntity.ok(updatedService);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/statuts")
    public List<Statut> getStatuts() {
        return statutRepository.findAll();
    }

    @PostMapping("/saveStatut")
    public Statut save(@Valid @RequestBody Statut statut) {
        if (statutRepository.existsByCode(statut.getCode())) {
            throw new BadRequestException("Le statut est déjà défini");
        }

        return statutRepository.save(statut);
    }

    @GetMapping("/statuts/{id}")
    public ResponseEntity<Statut> getStatutById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Statut statut = statutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("statut introuvable pour cet ", "id", id));
        return ResponseEntity.ok().body(statut);
    }

    @PutMapping("/statut/{id}")
    public ResponseEntity<Statut> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Statut statutDetails) throws ResourceNotFoundException {
        Statut statut = statutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statut introuvable pour cet ", "ID:", id));

        statut.setUpdateDate(JIDate.now());
        statut.setCode(statutDetails.getCode());
        statut.setLib(statutDetails.getLib());
        statut.setDescription(statutDetails.getDescription());
        final Statut updatedStatut = statutRepository.save(statut);
        return ResponseEntity.ok(updatedStatut);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/civilites")
    public List<Civilite> getCivilites() {
        return civiliteRepository.findAll();
    }

    @PostMapping("/saveCivilite")
    public Civilite save(@Valid @RequestBody Civilite civilite) {
        if (civiliteRepository.existsByCode(civilite.getCode())) {
            throw new BadRequestException("La civilitgé est déjà définie");
        }

        return civiliteRepository.save(civilite);
    }

    @GetMapping("/civilites/{id}")
    public ResponseEntity<Civilite> getCiviliteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Civilite civilite = civiliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Civilité introuvable pour cet ", "id", id));
        return ResponseEntity.ok().body(civilite);
    }

    @PutMapping("/civilites/{id}")
    public ResponseEntity<Civilite> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Civilite civiliteDetails) throws ResourceNotFoundException {
        Civilite civilite = civiliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Civilité introuvable pour cet ", "ID:", id));

        civilite.setUpdateDate(JIDate.now());
        civilite.setCode(civiliteDetails.getCode());
        civilite.setLib(civiliteDetails.getLib());
        civilite.setDescription(civiliteDetails.getDescription());
        final Civilite updatedCivilite = civiliteRepository.save(civilite);
        return ResponseEntity.ok(updatedCivilite);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/fonctions")
    public List<Fonction> getFonctions() {
        return fonctionRepository.findAll();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/jourFeries")
    public List<JourFerie> getJourFeries() {
        return jourFerieRepository.findAll();
    }

    @PostMapping(value = "/saveJourFerie")
    public JourFerie save(@Valid @RequestBody JourFerie jourFerie) {
        JourFerie j = jourFerieRepository.findByLib(jourFerie.getLib());
        JourFerie j1 = jourFerieRepository.findByJour(jourFerie.getJour());
        if (j != null) {
            throw new BadRequestException("La date est déjà définie.");
        } else if (j1 != null) {
            throw new BadRequestException("Le libellé est déjà définie.");
        } else {
            jourFerie.setJour(jourFerie.getJour());
            jourFerie.setLib(jourFerie.getLib());
            jourFerie.setAnnee(jourFerie.getAnnee());
            jourFerie.setEstRecurrent(jourFerie.isEstRecurrent());
            jourFerie.setEstFixe(jourFerie.isEstFixe());
            return jourFerieRepository.save(jourFerie);
        }
    }

    @GetMapping("/jourFeries/{id}")
    public ResponseEntity<JourFerie> getJourFerieById(@PathVariable(value = "id") Long jourFerieId) throws ResourceNotFoundException {
        JourFerie jourFerie = jourFerieRepository.findById(jourFerieId)
                .orElseThrow(() -> new ResourceNotFoundException("Jour férié introuvable pour cet ", "ID:", jourFerieId));
        return ResponseEntity.ok().body(jourFerie);
    }

    @PutMapping("/jourFeries/{id}")
    public ResponseEntity<JourFerie> update(@PathVariable(value = "id") Long jourFerieId, @Valid @RequestBody JourFerie jourFerieDetails) throws ResourceNotFoundException {
        JourFerie jourFerie = jourFerieRepository.findById(jourFerieId)
                .orElseThrow(() -> new ResourceNotFoundException("Jour Férié introuvable pour cet ", "ID:", jourFerieId));

        jourFerie.setUpdateDate(JIDate.now());
        jourFerie.setLib(jourFerieDetails.getLib());
        jourFerie.setJour(jourFerieDetails.getJour());
        jourFerie.setEstRecurrent(jourFerieDetails.isEstRecurrent());
        jourFerie.setEstFixe(jourFerieDetails.isEstFixe());
        jourFerie.setAnnee(jourFerieDetails.getAnnee());
        final JourFerie updatedJourFerie = jourFerieRepository.save(jourFerie);
        return ResponseEntity.ok(updatedJourFerie);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/congesNonDeductibles")
    public List<CongesNonDeductible> getCongesNonDeductibles() {
        return congesNonDeductibleRepository.findAll();
    }

    @PostMapping("/savecongesNonDeductible")
    public CongesNonDeductible save(@Valid @RequestBody CongesNonDeductible congesNonDeductible) {
        CongesNonDeductible cnd = congesNonDeductibleRepository.findByLib(congesNonDeductible.getLib());
        if (cnd != null) {
            throw new BadRequestException("Congé Non Deductible déjà définie.");
        } else {
            return congesNonDeductibleRepository.save(congesNonDeductible);
        }
    }

    @GetMapping("/congesNonDeductibles/{id}")
    public ResponseEntity<CongesNonDeductible> getCongesNonDeductibleById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        CongesNonDeductible conge = congesNonDeductibleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Congé Non Déductible introuvable pour cet ", "ID:", id));
        return ResponseEntity.ok().body(conge);
    }

    @PutMapping("/congesNonDeductibles/{id}")
    public ResponseEntity<CongesNonDeductible> update(@PathVariable(value = "id") Long congesNonDeductibleId, @Valid @RequestBody CongesNonDeductible congesNonDeductibleDetails) throws ResourceNotFoundException {
        CongesNonDeductible conge = congesNonDeductibleRepository.findById(congesNonDeductibleId)
                .orElseThrow(() -> new ResourceNotFoundException("Congé Non Déductible introuvable pour cet ", "ID:", congesNonDeductibleId));

        conge.setUpdateDate(JIDate.now());
        conge.setLib(congesNonDeductibleDetails.getLib());
        conge.setNbJourConges(congesNonDeductibleDetails.getNbJourConges());
        final CongesNonDeductible updatedCongesNonDeductible = congesNonDeductibleRepository.save(conge);
        return ResponseEntity.ok(updatedCongesNonDeductible);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/agents")
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }

    @PostMapping("/saveAgent")
    public Agent save(@Valid @RequestBody Agent agent) {
        Agent a;
        try {
            if (agentRepository.existsByCni(agent.getCni())) {
                throw new BadRequestException("Un autre agent possède le même numéro de CNI");
            } else if (agentRepository.existsByMatricule(agent.getMatricule())) {
                throw new BadRequestException("Un autre agent est déjà associé à ce numéro de matricule");
            } else if (agentRepository.existsByEmailProfessionnel(agent.getEmailProfessionnel())) {
                throw new BadRequestException("L'émail professionnel est celui d'un autre agent");
            } else {
                a = agentRepository.save(agent);
            }
        } catch (Exception e) {
            throw new BadRequestException("Impossible de créer l'agent", e);
        }
        return a;
    }

    @GetMapping("/agents/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent introuvable pour cet ", "id", id));
        return ResponseEntity.ok().body(agent);
    }

    @PutMapping("/agents/{id}")
    public ResponseEntity<Agent> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Agent agentDetails) throws ResourceNotFoundException {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent introuvable pour cet ", "ID:", id));

        final Agent updatedAgent = agentRepository.save(agent);
        return ResponseEntity.ok(updatedAgent);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/entites")
    public List<Entite> getEntites() {
        return entiteRepository.findAll();
    }

    @PostMapping("/saveEntite")
    public Entite save(@Valid @RequestBody Entite entite) {
        return entiteRepository.save(entite);
    }

    @GetMapping("/entites/{id}")
    public ResponseEntity<Entite> getEntiteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Entite entite = entiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entite introuvable pour cet ", "id", id));
        return ResponseEntity.ok().body(entite);
    }

    @PutMapping("/entites/{id}")
    public ResponseEntity<Entite> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Entite entiteDetails) throws ResourceNotFoundException {
        Entite entite = entiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entite introuvable pour cet ", "ID:", id));

        final Entite updatedEntite = entiteRepository.save(entite);
        return ResponseEntity.ok(updatedEntite);
    }
}
