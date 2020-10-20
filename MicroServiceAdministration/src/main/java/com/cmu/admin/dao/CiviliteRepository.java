package com.cmu.admin.dao;

import com.cmu.admin.entities.Civilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiviliteRepository extends JpaRepository<Civilite, Long> {
    Boolean existsByCode(String code);
}
