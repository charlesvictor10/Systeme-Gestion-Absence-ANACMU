package com.cmu.parametrage.dao;

import com.cmu.parametrage.entities.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Boolean existsByCni(String cni);

    Boolean existsByMatricule(String matricule);

    Boolean existsByEmailProfessionnel(String emailProfessionnel);

    @Query("select a from Agent a where a.emailProfessionnel like :x")
    public Page<Agent> chercheAgent(@Param("x") String emailProfessionnel, Pageable pageable);
}
