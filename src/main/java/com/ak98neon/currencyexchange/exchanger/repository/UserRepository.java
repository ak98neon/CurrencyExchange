package com.ak98neon.currencyexchange.exchanger.repository;

import com.ak98neon.currencyexchange.exchanger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(final String username);
}
