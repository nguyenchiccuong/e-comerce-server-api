package com.rookies.ecommerceapi.exception;

public class ReviewIdNotFoundException extends RuntimeException {

    public ReviewIdNotFoundException(String message) {
        super(message);
    }

    // public ReviewIdNotFoundException(Long ReviewId) {
    //     super("Review id: " + ReviewId + " not found");
    // }

}
