package com.vouchit.backend.exception.purchase;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PurchaseAdvice {

    @ResponseBody
    @ExceptionHandler(PurchaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String couponAlreadyPurchased(PurchaseException ex) {
        return ex.getMessage();
    }
}
