package com.ak98neon.currencyexchange.web;

import com.ak98neon.currencyexchange.model.service.CurrencyExchangeService;
import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/", produces = APPLICATION_JSON_VALUE)
public class ExchangerController {

    private final CurrencyExchangeService currencyExchangeService;

    public ExchangerController(@Qualifier("validateCurrencyExchangeService") final CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @PostMapping("/commissions")
    public CommissionsDto setCommissionsToCurrencyPair(final @RequestBody CommissionsDto commissionsDto) {
        return currencyExchangeService.setCommission(commissionsDto);
    }

    @GetMapping("/commissions")
    public List<CommissionsDto> getCommissionList() {
        return CommissionsDto.ofList(
                currencyExchangeService.getCommission()
        );
    }
}
