package com.cmu.parametrage.dao;

import com.cmu.parametrage.entities.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {
    Boolean existsByCode(String code);
}
