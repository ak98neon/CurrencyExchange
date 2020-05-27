package com.ak98neon.currencyexchange.exchanger.service;

import com.ak98neon.currencyexchange.exchanger.entity.CurrencyId;
import com.ak98neon.currencyexchange.exchanger.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRate, CurrencyId> {
    ExchangeRate findByCurrencyId(final CurrencyId currencyId);
}
