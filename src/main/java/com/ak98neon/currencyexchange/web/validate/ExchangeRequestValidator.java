package com.ak98neon.currencyexchange.web.validate;

import com.ak98neon.currencyexchange.exchanger.entity.CurrencyId;
import com.ak98neon.currencyexchange.exchanger.repository.CommissionRepository;
import com.ak98neon.currencyexchange.exchanger.repository.ExchangeRatesRepository;
import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class ExchangeRequestValidator implements ConstraintValidator<ExchangeRequestConstraint, ExchangeRequest> {
    private final CommissionRepository commissionRepository;
    private final ExchangeRatesRepository exchangeRatesRepository;

    public ExchangeRequestValidator(final CommissionRepository commissionRepository,
                                    final ExchangeRatesRepository exchangeRatesRepository) {
        this.commissionRepository = commissionRepository;
        this.exchangeRatesRepository = exchangeRatesRepository;
    }

    public boolean isValid(ExchangeRequest exchangeRequest, ConstraintValidatorContext context) {
        final String from = exchangeRequest.getCurrencyFrom();
        final String to = exchangeRequest.getCurrencyTo();

        if (StringUtils.isEmpty(from) || StringUtils.isEmpty(to) || from.equalsIgnoreCase(to)) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Currencies must be not empty and not equals")
                    .addConstraintViolation();
            return false;
        }

        final CurrencyId currencyId = new CurrencyId(from, to);
        final CurrencyId reverseCurrencyId = new CurrencyId(to, from);
        if (commissionRepository.findByCurrencyId(currencyId) == null ||
                commissionRepository.findByCurrencyId(reverseCurrencyId) == null) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Sorry, commission isn't configured for these currencies")
                    .addConstraintViolation();
            return false;
        }

        if (exchangeRatesRepository.findByCurrencyId(currencyId) == null ||
                exchangeRatesRepository.findByCurrencyId(reverseCurrencyId) == null) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Sorry, exchange rates isn't configured for these currencies")
                    .addConstraintViolation();
            return false;
        }

        final BigDecimal amountFrom = exchangeRequest.getAmountFrom();
        if (amountFrom.doubleValue() < 0.0) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Amount from must be more than zero value")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
