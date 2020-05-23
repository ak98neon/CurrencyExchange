package com.ak98neon.currencyexchange.web.validate;

import com.ak98neon.currencyexchange.model.service.CurrencyExchangeService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public interface ValidateCurrencyExchangeService extends CurrencyExchangeService {
    static ValidateCurrencyExchangeService newProxyOf(final CurrencyExchangeService delegate, final Validator validator) {
        Objects.requireNonNull(delegate);
        Objects.requireNonNull(validator);
        final Class<?>[] c = {ValidateCurrencyExchangeService.class};

        class Handler implements InvocationHandler {
            private final CurrencyExchangeService d;
            private final Set<Method> validatedMethods =
                    new HashSet<>(Arrays.asList(CurrencyExchangeService.class.getDeclaredMethods()));

            private Handler(CurrencyExchangeService d) {
                this.d = d;
            }

            @Override
            public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                if (args != null && validatedMethods.contains(method)) {
                    final Set<ConstraintViolation<CurrencyExchangeService>> violations =
                            validator.forExecutables().validateParameters(delegate, method, args);
                    if (!violations.isEmpty()) {
                        throw new ConstraintViolationException(violations);
                    }
                }
                return method.invoke(d, args);
            }
        }

        return (ValidateCurrencyExchangeService)
                Proxy.newProxyInstance(ValidateCurrencyExchangeService.class.getClassLoader(), c, new Handler(delegate));
    }
}
