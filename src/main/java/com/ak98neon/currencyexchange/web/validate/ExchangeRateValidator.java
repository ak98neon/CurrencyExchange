package com.ak98neon.currencyexchange.web.validate;

import com.ak98neon.currencyexchange.web.dto.ExchangeRateDto;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExchangeRateValidator implements ConstraintValidator<ExchangeRateConstraint, ExchangeRateDto> {

    @Override
    public boolean isValid(ExchangeRateDto obj, ConstraintValidatorContext context) {
        final Double rate = obj.getRate();

        if (rate < 0) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Rate must be more than zero value")
                    .addConstraintViolation();
            return false;
        }

        if (StringUtils.isEmpty(obj.getFrom())
                || StringUtils.isEmpty(obj.getTo())
                || obj.getFrom().equalsIgnoreCase(obj.getTo())) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Name of currency isn't valid or empty")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
