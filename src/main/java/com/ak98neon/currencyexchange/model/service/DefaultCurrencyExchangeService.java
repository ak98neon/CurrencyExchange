package com.ak98neon.currencyexchange.model.service;

import com.ak98neon.currencyexchange.model.Commission;
import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCurrencyExchangeService implements CurrencyExchangeService {

    private CommissionRepository commissionRepository;

    public DefaultCurrencyExchangeService(final CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
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
}
