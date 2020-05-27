package com.ak98neon.currencyexchange.exchanger.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "commissions")
@Data
@NoArgsConstructor
public class Commission {
    @EmbeddedId
    private CurrencyId currencyId;
    private Double value;

    public Commission(final CurrencyId currencyId,
                      final Double value) {
        this.currencyId = currencyId;
        this.value = value;
    }
}
