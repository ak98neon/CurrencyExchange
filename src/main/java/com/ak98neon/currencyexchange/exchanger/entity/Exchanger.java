package com.ak98neon.currencyexchange.exchanger.entity;

import com.ak98neon.currencyexchange.exchanger.repository.CommissionRepository;
import com.ak98neon.currencyexchange.exchanger.repository.ExchangeRatesRepository;
import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class Exchanger {
    private final CommissionRepository commissionRepository;
    private final ExchangeRatesRepository exchangeRatesRepository;

    public Exchanger(final CommissionRepository commissionRepository,
                     final ExchangeRatesRepository exchangeRatesRepository) {
        this.commissionRepository = commissionRepository;
        this.exchangeRatesRepository = exchangeRatesRepository;
    }

    public ExchangeRequest exchangeGive(final ExchangeRequest exchangeRequest) {
        final String currencyFrom = exchangeRequest.getCurrencyFrom();
        final String currencyTo = exchangeRequest.getCurrencyTo();

        final CurrencyId currencyId = new CurrencyId(currencyFrom, currencyTo);
        final Commission commission = commissionRepository.findByCurrencyId(currencyId);
        final ExchangeRate exchangeRate = exchangeRatesRepository.findByCurrencyId(currencyId);

        final BigDecimal amountFrom = exchangeRequest.getAmountFrom();

        BigDecimal multiply = amountFrom.multiply(exchangeRate.getRate());
        exchangeRequest.setAmountFrom(calcPercent(multiply, commission).setScale(2, RoundingMode.HALF_EVEN));
        return exchangeRequest;
    }

    public ExchangeRequest exchangeGet(ExchangeRequest exchangeRequest) {
        final String currencyFrom = exchangeRequest.getCurrencyFrom();
        final String currencyTo = exchangeRequest.getCurrencyTo();

        final CurrencyId currencyId = new CurrencyId(currencyTo, currencyFrom);
        final Commission commission = commissionRepository.findByCurrencyId(currencyId);
        final ExchangeRate exchangeRate = exchangeRatesRepository.findByCurrencyId(currencyId);

        final BigDecimal amountTo = exchangeRequest.getAmountTo();

        BigDecimal multiply = amountTo.multiply(exchangeRate.getRate());
        exchangeRequest.setAmountFrom(calcPercent(multiply, commission).setScale(2, RoundingMode.HALF_EVEN));
        return exchangeRequest;
    }

    public BigDecimal calcPercent(final BigDecimal amount,
                                  final Commission commission) {
        BigDecimal decimalCommissionValue = commission.getValue().divide(new BigDecimal("100"));
        return amount.subtract(amount.multiply(decimalCommissionValue));
    }
}
