package com.cmu.parametrage.dao;

import com.cmu.parametrage.entities.Civilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiviliteRepository extends JpaRepository<Civilite, Long> {
    Boolean existsByCode(String code);
}
