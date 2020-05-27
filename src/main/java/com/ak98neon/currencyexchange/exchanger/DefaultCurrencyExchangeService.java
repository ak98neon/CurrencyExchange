package com.ak98neon.currencyexchange.exchanger;

import com.ak98neon.currencyexchange.CurrencyExchangerException;
import com.ak98neon.currencyexchange.exchanger.entity.Commission;
import com.ak98neon.currencyexchange.exchanger.entity.CurrencyId;
import com.ak98neon.currencyexchange.exchanger.entity.ExchangeRate;
import com.ak98neon.currencyexchange.exchanger.entity.Exchanger;
import com.ak98neon.currencyexchange.exchanger.repository.CommissionRepository;
import com.ak98neon.currencyexchange.exchanger.repository.ExchangeRatesRepository;
import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRateDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import com.ak98neon.currencyexchange.web.dto.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.ak98neon.currencyexchange.util.DecimalUtils.round;

@Slf4j
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
    @Transactional
    public CommissionsDto setCommission(final CommissionsDto commissionsDto) {
        log.info("Set new commission to currencies pair: " + commissionsDto);
        final Commission commission = commissionsDto.to();
        commissionRepository.saveAndFlush(commission);

        final CurrencyId backwardCurrencyId = new CurrencyId(commission.getCurrencyId().getTo(), commission.getCurrencyId().getFrom());
        final Commission backwardCommission = new Commission(backwardCurrencyId, commission.getValue());
        commissionRepository.saveAndFlush(backwardCommission);
        return commissionsDto;
    }

    @Override
    public List<Commission> getCommission() {
        log.info("Fetch all commissions");
        return commissionRepository.findAll();
    }

    @Override
    @Transactional
    public ExchangeRateDto setExchangeRate(ExchangeRateDto exchangeRateDto) {
        log.info("Setting new exchange rate to currencies pair: " + exchangeRateDto);
        exchangeRatesRepository.saveAndFlush(exchangeRateDto.to());

        final BigDecimal backwardRate = new BigDecimal("1")
                .divide(exchangeRateDto.getRate().divide(new BigDecimal("1")));

        final ExchangeRate backwardExchangeRate =
                new ExchangeRate(
                        new CurrencyId(exchangeRateDto.getTo(), exchangeRateDto.getFrom()),
                        round(backwardRate, 3));

        exchangeRatesRepository.saveAndFlush(backwardExchangeRate);
        log.info("Successfully sot new exchange rate to currencies pair");
        return exchangeRateDto;
    }

    @Override
    public List<ExchangeRate> getExchangeRate() {
        log.info("Fetch all exchange rates");
        return exchangeRatesRepository.findAll();
    }

    @Override
    @Transactional
    public ExchangeRequest exchange(ExchangeRequest exchangeRequest) {
        log.info("Exchange currencies: " + exchangeRequest);
        final OperationType operationType = exchangeRequest.getOperationType();

        if (operationType.equals(OperationType.GET)) {
            return exchanger.exchangeGet(exchangeRequest);
        } else if (operationType.equals(OperationType.GIVE)) {
            return exchanger.exchangeGive(exchangeRequest);
        }
        throw new CurrencyExchangerException("UnsupportedOperation with exchange request");
    }
}
