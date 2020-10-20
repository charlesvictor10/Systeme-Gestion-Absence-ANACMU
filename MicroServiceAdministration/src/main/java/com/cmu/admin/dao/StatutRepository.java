package com.cmu.admin.dao;

import com.cmu.admin.entities.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {
    Boolean existsByCode(String code);
}
