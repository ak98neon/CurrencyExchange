package com.ak98neon.currencyexchange.web.validate;

import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class ExchangeRequestValidator implements ConstraintValidator<ExchangeRequestConstraint, ExchangeRequest> {
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
