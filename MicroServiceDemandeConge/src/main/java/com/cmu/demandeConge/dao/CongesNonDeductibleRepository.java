package com.cmu.demandeConge.dao;

import com.cmu.demandeConge.entities.CongesNonDeductible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CongesNonDeductibleRepository extends JpaRepository<CongesNonDeductible, Long> {
    public CongesNonDeductible findByLib(String lib);
}
