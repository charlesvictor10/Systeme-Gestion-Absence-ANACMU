package com.cmu.parametrage.dao;

import com.cmu.parametrage.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByCode(String code);

    public Role findByCode(String code);
}
