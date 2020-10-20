package com.cmu.parametrage.dao;

import com.cmu.parametrage.entities.Entite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntiteRepository extends JpaRepository<Entite, Long> {
}
