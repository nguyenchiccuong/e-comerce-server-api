package com.rookies.ecommerceapi.exception;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(String message) {
        super(message);
    }

    // public UserIdNotFoundException(Long id) {
    //     super("Could not find user with id = " + id);
    // }

}
