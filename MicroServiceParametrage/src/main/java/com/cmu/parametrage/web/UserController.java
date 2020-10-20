package com.cmu.parametrage.web;

import com.cmu.parametrage.Exception.ResourceNotFoundException;
import com.cmu.parametrage.dao.UtilisateurRepository;
import com.cmu.parametrage.entities.Utilisateur;
import com.cmu.parametrage.security.CurrentUser;
import com.cmu.parametrage.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/currentUser")
    public Utilisateur getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return utilisateurRepository.findById(userPrincipal.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", userPrincipal.getId()));
    }
}
