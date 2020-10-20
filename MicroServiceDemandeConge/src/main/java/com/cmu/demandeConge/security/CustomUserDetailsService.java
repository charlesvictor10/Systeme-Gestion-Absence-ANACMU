package com.cmu.demandeConge.security;

import com.cmu.demandeConge.Exception.ResourceNotFoundException;
import com.cmu.demandeConge.dao.UtilisateurRepository;
import com.cmu.demandeConge.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("L'utilisateur avec l'adresse email:" + email + "n'existe pas")
                );

        return UserPrincipal.create(utilisateur);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", id)
                );

        return UserPrincipal.create(utilisateur);
    }
}
