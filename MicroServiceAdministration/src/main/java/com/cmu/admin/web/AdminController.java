package com.cmu.admin.web;

import com.cmu.admin.Exception.ResourceNotFoundException;
import com.cmu.admin.dao.RoleRepository;
import com.cmu.admin.dao.UtilisateurRepository;
import com.cmu.admin.entities.AuthProvider;
import com.cmu.admin.entities.Role;
import com.cmu.admin.entities.Utilisateur;
import com.cmu.admin.util.JIDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/utilisateurs")
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurRepository.findAll();
    }

    @GetMapping(value = "/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable pour cet", "id", id));
        return ResponseEntity.ok().body(utilisateur);
    }

    @PutMapping(value = "/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Utilisateur utilisateurDetail) throws ResourceNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable pour cet", "id", id));

       /* utilisateur.setUpdateDate(JIDate.now());
        utilisateur.setActived(utilisateurDetail.isActived());
        utilisateur.setRaisonBlocage(utilisateurDetail.getRaisonBlocage());
        utilisateur.setProvider(AuthProvider.local);
        utilisateur.setDoitChangerSonMdp(false);*/
        final Utilisateur updateUtilisateur = utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok(updateUtilisateur);
    }

    @DeleteMapping(value = "/utilisateur/{id}")
    public void deleteUtilisateur(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable pour cet", "id", id));
        utilisateurRepository.delete(utilisateur);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> listRole() {
        return roleRepository.findAll();
    }

    @DeleteMapping(value = "/roles/{id}")
    public void deleteRole(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rôle introuvable pour cet", "id",  id));

        roleRepository.delete(role);
    }
}
