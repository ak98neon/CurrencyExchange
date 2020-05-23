package com.ak98neon.currencyexchange.model;

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
    private CommissionId commissionId;
    private Long value;

    public Commission(final CommissionId commissionId,
                      final Long value) {
        this.commissionId = commissionId;
        this.value = value;
    }
}
