package com.ak98neon.currencyexchange.exchanger.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "exchange_rates")
@Data
@NoArgsConstructor
public class ExchangeRate {
    @EmbeddedId
    private CurrencyId currencyId;
    private BigDecimal rate;

    public ExchangeRate(final CurrencyId currencyId,
                        final BigDecimal rate) {
        this.currencyId = currencyId;
        this.rate = rate;
    }
}
