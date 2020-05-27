package com.ak98neon.currencyexchange.web.dto;

import com.ak98neon.currencyexchange.exchanger.entity.CurrencyId;
import com.ak98neon.currencyexchange.exchanger.entity.ExchangeRate;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class ExchangeRateDto {
    private String from;
    private String to;
    private BigDecimal rate;

    @JsonCreator
    public ExchangeRateDto(final String from,
                           final String to,
                           final BigDecimal rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public static ExchangeRateDto of(ExchangeRate exchangeRate) {
        return new ExchangeRateDto(
                exchangeRate.getCurrencyId().getFrom(),
                exchangeRate.getCurrencyId().getTo(),
                exchangeRate.getRate()
        );
    }

    public static List<ExchangeRateDto> ofList(final List<ExchangeRate> exchangeRateList) {
        return exchangeRateList.stream().map(ExchangeRateDto::of).collect(Collectors.toList());
    }

    public ExchangeRate to() {
        return new ExchangeRate(
                new CurrencyId(this.from, this.to),
                this.rate
        );
    }
}
