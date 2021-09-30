package com.bojan.vending.exception;

public class AmountNotSufficientException extends RuntimeException{

    public AmountNotSufficientException(String message) {
        super(message);
    }
}
