package com.ak98neon.currencyexchange.model.service;

import com.ak98neon.currencyexchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(final String username);
}
