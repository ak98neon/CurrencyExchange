package com.ak98neon.currencyexchange.model.service;

import com.ak98neon.currencyexchange.CurrencyExchangerException;
import com.ak98neon.currencyexchange.model.Commission;
import com.ak98neon.currencyexchange.model.CurrencyId;
import com.ak98neon.currencyexchange.model.ExchangeRate;
import com.ak98neon.currencyexchange.model.Exchanger;
import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRateDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import com.ak98neon.currencyexchange.web.dto.OperationType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ak98neon.currencyexchange.util.DecimalUtils.round;

@Service
public class DefaultCurrencyExchangeService implements CurrencyExchangeService {

    private final CommissionRepository commissionRepository;
    private final ExchangeRatesRepository exchangeRatesRepository;
    private final Exchanger exchanger;

    public DefaultCurrencyExchangeService(final CommissionRepository commissionRepository,
                                          final ExchangeRatesRepository exchangeRatesRepository,
                                          final Exchanger exchanger) {
        this.commissionRepository = commissionRepository;
        this.exchangeRatesRepository = exchangeRatesRepository;
        this.exchanger = exchanger;
    }

    @Override
    public CommissionsDto setCommission(final CommissionsDto commissionsDto) {
        commissionRepository.saveAndFlush(commissionsDto.to());
        return commissionsDto;
    }

    @Override
    public List<Commission> getCommission() {
        return commissionRepository.findAll();
    }

    @Override
    @Transactional
    public ExchangeRateDto setExchangeRate(ExchangeRateDto exchangeRateDto) {
        exchangeRatesRepository.saveAndFlush(exchangeRateDto.to());

        final double backwardRate = 1 / (exchangeRateDto.getRate() / 1);
        final ExchangeRate backwardExchangeRate =
                new ExchangeRate(
                        new CurrencyId(exchangeRateDto.getTo(), exchangeRateDto.getFrom()),
                        round(backwardRate, 3));
        exchangeRatesRepository.saveAndFlush(backwardExchangeRate);
        return exchangeRateDto;
    }

    @Override
    public List<ExchangeRate> getExchangeRate() {
        return exchangeRatesRepository.findAll();
    }

    @Override
    @Transactional
    public ExchangeRequest exchange(ExchangeRequest exchangeRequest) {
        final OperationType operationType = exchangeRequest.getOperationType();

        if (operationType.equals(OperationType.GET)) {
            return exchanger.exchangeGet(exchangeRequest);
        } else if (operationType.equals(OperationType.GIVE)) {
            return exchanger.exchangeGive(exchangeRequest);
        }
        throw new CurrencyExchangerException("UnsupportedOperation with exchange request");
    }
}
