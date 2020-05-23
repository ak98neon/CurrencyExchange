package com.ak98neon.currencyexchange.web.validate;

import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommissionValidator implements ConstraintValidator<CommissionConstraint, CommissionsDto> {

    @Override
    public boolean isValid(CommissionsDto commissionsDto, ConstraintValidatorContext constraintValidatorContext) {
        final Double commissionPt = commissionsDto.getCommissionPt();

        if (commissionPt < 0.0 || commissionPt > 100.0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Cannot set a new commission, value less or more than 0.0 : 100.0")
                    .addPropertyNode("commissionPt")
                    .addConstraintViolation();
            return false;
        }

        if (commissionsDto.getFrom().equalsIgnoreCase(commissionsDto.getTo())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Mustn't set commission to the same currency")
                    .addConstraintViolation();
            return false;
        }

        final String from = commissionsDto.getFrom();
        final String to = commissionsDto.getTo();

        if (StringUtils.isEmpty(from) || StringUtils.isEmpty(to)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Name of currency isn't valid or empty")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
