package com.ak98neon.currencyexchange.exchanger.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "commissions")
@Data
@NoArgsConstructor
public class Commission {
    @EmbeddedId
    private CurrencyId currencyId;
    private BigDecimal value;

    public Commission(final CurrencyId currencyId,
                      final BigDecimal value) {
        this.currencyId = currencyId;
        this.value = value;
    }
}
