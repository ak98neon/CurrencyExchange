package com.ak98neon.currencyexchange.web.dto;

import com.ak98neon.currencyexchange.model.Commission;
import com.ak98neon.currencyexchange.model.CommissionId;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CommissionsDto {
    private Long commissionPt;
    private String from;
    private String to;

    @JsonCreator
    public CommissionsDto(final Long commissionPt,
                          final String from,
                          final String to) {
        this.commissionPt = commissionPt;
        this.from = from;
        this.to = to;
    }

    public static CommissionsDto of(final Commission commission) {
        return new CommissionsDto(
                commission.getValue(),
                commission.getCommissionId().getFrom(),
                commission.getCommissionId().getTo()
        );
    }

    public static List<CommissionsDto> ofList(final List<Commission> commissions) {
        return commissions.stream().map(CommissionsDto::of).collect(Collectors.toList());
    }

    public Commission to() {
        return new Commission(new CommissionId(this.from, this.to), this.commissionPt);
    }
}
