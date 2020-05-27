package com.ak98neon.currencyexchange.exchanger;

import com.ak98neon.currencyexchange.exchanger.entity.Commission;
import com.ak98neon.currencyexchange.exchanger.entity.ExchangeRate;
import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRateDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import com.ak98neon.currencyexchange.web.validate.CommissionConstraint;
import com.ak98neon.currencyexchange.web.validate.ExchangeRateConstraint;
import com.ak98neon.currencyexchange.web.validate.ExchangeRequestConstraint;

import java.util.List;

public interface CurrencyExchangeService {

    CommissionsDto setCommission(final @CommissionConstraint CommissionsDto commissionsDto);

    List<Commission> getCommission();

    ExchangeRateDto setExchangeRate(final @ExchangeRateConstraint ExchangeRateDto exchangeRateDto);

    List<ExchangeRate> getExchangeRate();

    ExchangeRequest exchange(final @ExchangeRequestConstraint ExchangeRequest exchangeRequest);
}
