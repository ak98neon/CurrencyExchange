package com.ak98neon.currencyexchange.model.service;

import com.ak98neon.currencyexchange.model.Commission;
import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import com.ak98neon.currencyexchange.web.validate.CommissionConstraint;

import java.util.List;

public interface CurrencyExchangeService {

    CommissionsDto setCommission(final @CommissionConstraint CommissionsDto commissionsDto);

    List<Commission> getCommission();
}
