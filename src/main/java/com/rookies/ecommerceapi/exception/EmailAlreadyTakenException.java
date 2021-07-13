package com.rookies.ecommerceapi.exception;

public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException(String message) {
        super(message);
    }

}
