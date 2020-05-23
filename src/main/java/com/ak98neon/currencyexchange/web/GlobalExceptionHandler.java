package com.ak98neon.currencyexchange.web;

import com.ak98neon.currencyexchange.web.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorResponse handleValidationExceptions(final HttpServletResponse resp, final Exception exception) {
        return new ErrorResponse(exception.getLocalizedMessage());
    }
}
