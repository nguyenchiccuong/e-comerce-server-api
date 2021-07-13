package com.rookies.ecommerceapi.exception;

public class UsernameUnmatchWithReviewException extends RuntimeException {

    public UsernameUnmatchWithReviewException(String message) {
        super(message);
    }

    // public UsernameUnmatchWithReviewException(String username) {
    //     super("Username: " + username + " unmatch with review");
    // }

}
