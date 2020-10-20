package com.cmu.parametrage.dao;

import com.cmu.parametrage.entities.CongesNonDeductible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CongesNonDeductibleRepository extends JpaRepository<CongesNonDeductible, Long> {
    public CongesNonDeductible findByLib(String lib);

    @Query("select c from CongesNonDeductible c where c.lib like :x")
    public Page<CongesNonDeductible> cherche(@Param("x") String lib, Pageable pageable);
}
