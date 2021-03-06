package com.cmu.admin.dao;

import com.cmu.admin.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Boolean existsByCni(String cni);
    Boolean existsByMatricule(String matricule);
    Boolean existsByEmailProfessionnel(String emailProfessionnel);
}
