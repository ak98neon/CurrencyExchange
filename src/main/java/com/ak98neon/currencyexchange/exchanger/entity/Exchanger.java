package com.ak98neon.currencyexchange.exchanger.entity;

import com.ak98neon.currencyexchange.exchanger.repository.CommissionRepository;
import com.ak98neon.currencyexchange.exchanger.repository.ExchangeRatesRepository;
import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import com.ak98neon.currencyexchange.web.dto.OperationType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.ak98neon.currencyexchange.web.dto.OperationType.GET;
import static com.ak98neon.currencyexchange.web.dto.OperationType.GIVE;

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
        exchangeRequest.setAmountTo(calcPercent(multiply, commission, GIVE).setScale(2, RoundingMode.HALF_EVEN));
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
        exchangeRequest.setAmountFrom(calcPercent(multiply, commission, GET).setScale(2, RoundingMode.HALF_EVEN));
        return exchangeRequest;
    }

    public BigDecimal calcPercent(final BigDecimal amount,
                                  final Commission commission,
                                  final OperationType operationType) {
        BigDecimal decimalCommissionValue = commission.getValue().divide(new BigDecimal("100"));
        final BigDecimal amountWithCommission = amount.multiply(decimalCommissionValue);
        return operationType.equals(GIVE) ? amount.subtract(amountWithCommission) : amount.add(amountWithCommission);
    }
}
