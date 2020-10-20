package com.cmu.admin.dao;

import com.cmu.admin.entities.Etat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtatRepository extends JpaRepository<Etat,Long> {
}
