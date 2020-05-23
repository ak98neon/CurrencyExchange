package com.ak98neon.currencyexchange.model.service;

import com.ak98neon.currencyexchange.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
