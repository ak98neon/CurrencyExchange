package com.ak98neon.currencyexchange.model.service;

import com.ak98neon.currencyexchange.model.CurrencyId;
import com.ak98neon.currencyexchange.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRate, CurrencyId> {
    ExchangeRate findByCurrencyId(final CurrencyId currencyId);
}
