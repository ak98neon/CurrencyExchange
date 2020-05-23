package com.ak98neon.currencyexchange.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ExchangeRequest {
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private String currencyFrom;
    private String currencyTo;
    private OperationType operationType;

    @JsonCreator
    public ExchangeRequest(final BigDecimal amountFrom,
                           final BigDecimal amountTo,
                           final String currencyFrom,
                           final String currencyTo,
                           final OperationType operationType) {
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.operationType = operationType;
    }
}
