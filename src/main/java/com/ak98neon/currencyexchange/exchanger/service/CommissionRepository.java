package com.ak98neon.currencyexchange.exchanger.service;

import com.ak98neon.currencyexchange.exchanger.entity.Commission;
import com.ak98neon.currencyexchange.exchanger.entity.CurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, CurrencyId> {
    Commission findByCurrencyId(final CurrencyId currencyId);
}
