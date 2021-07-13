package com.rookies.ecommerceapi.exception;

public class UserLockedException extends RuntimeException {

    public UserLockedException(String message) {
        super(message);
    }

    // public UserLockedException(String username) {
    //     super("Username: " + username + " is locked");
    // }

}