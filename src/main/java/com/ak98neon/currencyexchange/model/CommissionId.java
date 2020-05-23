package com.ak98neon.currencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionId implements Serializable {

    @Column(name = "from_currency")
    private String from;
    @Column(name = "to_currency")
    private String to;
}
