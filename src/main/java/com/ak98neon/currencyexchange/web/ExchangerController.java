package com.ak98neon.currencyexchange.web;

import com.ak98neon.currencyexchange.model.service.CurrencyExchangeService;
import com.ak98neon.currencyexchange.web.dto.CommissionsDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRateDto;
import com.ak98neon.currencyexchange.web.dto.ExchangeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@Api(value = "Exchanger controller")
public class ExchangerController {

    private final CurrencyExchangeService currencyExchangeService;

    public ExchangerController(@Qualifier("validateCurrencyExchangeService") final CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @ApiOperation(value = "Set a new commissions to pair of currencies", response = CommissionsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully set a new commissions"),
            @ApiResponse(code = 401, message = "You are not authorized to set the commission"),
    })
    @PostMapping("/commissions")
    public CommissionsDto setCommissionsToCurrencyPair(final @RequestBody CommissionsDto commissionsDto) {
        return currencyExchangeService.setCommission(commissionsDto);
    }

    @ApiOperation(value = "View a list of available commissions", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @GetMapping("/commissions")
    public List<CommissionsDto> getCommissionList() {
        return CommissionsDto.ofList(
                currencyExchangeService.getCommission()
        );
    }

    @ApiOperation(value = "Set a new exchange rates to currencies pair", response = ExchangeRateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully sot exchange rates"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @PostMapping("/exchange-rates")
    public ExchangeRateDto setExchangeRates(final @RequestBody ExchangeRateDto exchangeRateDto) {
        return currencyExchangeService.setExchangeRate(exchangeRateDto);
    }

    @ApiOperation(value = "View a list of available exchange rates", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @GetMapping("/exchange-rates")
    public List<ExchangeRateDto> getExchangeRatesList() {
        return ExchangeRateDto.ofList(
                currencyExchangeService.getExchangeRate()
        );
    }

    @ApiOperation(value = "Exchange currencies", response = ExchangeRequest.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully exchanged"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @PostMapping("/exchange")
    public ExchangeRequest exchangeRequest(final @RequestBody ExchangeRequest exchangeRequest) {
        return currencyExchangeService.exchange(exchangeRequest);
    }
}
