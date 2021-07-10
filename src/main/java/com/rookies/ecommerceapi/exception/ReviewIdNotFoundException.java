package com.rookies.ecommerceapi.exception;

public class ReviewIdNotFoundException extends RuntimeException {

    public ReviewIdNotFoundException(Long ReviewId) {
        super("Review id: " + ReviewId + " not found");
    }

}
