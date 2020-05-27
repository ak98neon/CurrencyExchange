package com.ak98neon.currencyexchange.web.dto;

import com.ak98neon.currencyexchange.exchanger.entity.Commission;
import com.ak98neon.currencyexchange.exchanger.entity.CurrencyId;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CommissionsDto {
    private BigDecimal commissionPt;
    private String from;
    private String to;

    @JsonCreator
    public CommissionsDto(final BigDecimal commissionPt,
                          final String from,
                          final String to) {
        this.commissionPt = commissionPt;
        this.from = from;
        this.to = to;
    }

    public static CommissionsDto of(final Commission commission) {
        return new CommissionsDto(
                commission.getValue(),
                commission.getCurrencyId().getFrom(),
                commission.getCurrencyId().getTo()
        );
    }

    public static List<CommissionsDto> ofList(final List<Commission> commissions) {
        return commissions.stream().map(CommissionsDto::of).collect(Collectors.toList());
    }

    public Commission to() {
        return new Commission(new CurrencyId(this.from, this.to), this.commissionPt);
    }
}
