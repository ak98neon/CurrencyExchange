package com.ak98neon.currencyexchange.web.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Constraint(validatedBy = ExchangeRequestValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExchangeRequestConstraint {
    String message() default "Invalid exchange request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
