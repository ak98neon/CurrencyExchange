package com.ak98neon.currencyexchange.config;

import com.ak98neon.currencyexchange.exchanger.service.CurrencyExchangeService;
import com.ak98neon.currencyexchange.web.validate.ValidateCurrencyExchangeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validator;

@Configuration
public class CurrencyExchangeConfiguration {
    @Bean
    public ValidateCurrencyExchangeService validateCurrencyExchangeService(CurrencyExchangeService currencyExchangeService, Validator validator) {
        return ValidateCurrencyExchangeService.newProxyOf(currencyExchangeService, validator);
    }
}
