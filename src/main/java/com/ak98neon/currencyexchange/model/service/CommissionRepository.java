package com.ak98neon.currencyexchange.model.service;

import com.ak98neon.currencyexchange.model.Commission;
import com.ak98neon.currencyexchange.model.CurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, CurrencyId> {
    Commission findByCurrencyId(final CurrencyId currencyId);
}
