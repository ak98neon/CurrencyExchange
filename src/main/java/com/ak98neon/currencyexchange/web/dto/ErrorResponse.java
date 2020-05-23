package com.ak98neon.currencyexchange.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorResponse {
    private String errorMsg;

    @JsonCreator
    public ErrorResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
