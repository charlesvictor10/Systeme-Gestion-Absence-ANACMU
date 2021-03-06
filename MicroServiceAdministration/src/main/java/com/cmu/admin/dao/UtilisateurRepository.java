package com.cmu.admin.dao;

import com.cmu.admin.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    Utilisateur findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
