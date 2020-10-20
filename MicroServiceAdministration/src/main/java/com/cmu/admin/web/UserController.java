package com.cmu.admin.web;

import com.cmu.admin.Exception.ResourceNotFoundException;
import com.cmu.admin.dao.UtilisateurRepository;
import com.cmu.admin.entities.Utilisateur;
import com.cmu.admin.security.CurrentUser;
import com.cmu.admin.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/currentUser")
    /*@PreAuthorize("hasRole('USER')")*/
    public Utilisateur getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return utilisateurRepository.findById(userPrincipal.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", userPrincipal.getId()));
    }
}
