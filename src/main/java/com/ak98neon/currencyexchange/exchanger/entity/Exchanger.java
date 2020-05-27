package com.ak98neon.currencyexchange.exchanger.entity;

import com.ak98neon.currencyexchange.exchanger.service.CommissionRepository;
import com.ak98neon.currencyexchange.exchanger.service.ExchangeRatesRepository;
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

        final BigDecimal multiply = amountFrom.multiply(BigDecimal.valueOf(exchangeRate.getRate()));
        exchangeRequest.setAmountTo(multiply.subtract(calcPercent(multiply, commission)));
        return exchangeRequest;
    }

    public ExchangeRequest exchangeGet(ExchangeRequest exchangeRequest) {
        final String currencyFrom = exchangeRequest.getCurrencyFrom();
        final String currencyTo = exchangeRequest.getCurrencyTo();

        final CurrencyId currencyId = new CurrencyId(currencyTo, currencyFrom);
        final Commission commission = commissionRepository.findByCurrencyId(currencyId);
        final ExchangeRate exchangeRate = exchangeRatesRepository.findByCurrencyId(currencyId);

        final BigDecimal amountTo = exchangeRequest.getAmountTo();

        final BigDecimal multiply = amountTo.multiply(BigDecimal.valueOf(exchangeRate.getRate()));
        exchangeRequest.setAmountFrom(multiply.subtract(calcPercent(multiply, commission)));
        return exchangeRequest;
    }

    public BigDecimal calcPercent(final BigDecimal amount,
                                  final Commission commission) {
        return amount
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN)
                .multiply(BigDecimal.valueOf(commission.getValue()));
    }
}
