package com.rookies.ecommerceapi.exception;

public class OriginIdNotFoundException extends RuntimeException {

    public OriginIdNotFoundException(Integer originId) {
        super("Origin id: " + originId + " not found");
    }

}
