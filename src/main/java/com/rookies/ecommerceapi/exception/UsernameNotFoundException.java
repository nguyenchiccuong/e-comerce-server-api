package com.rookies.ecommerceapi.exception;

public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException(String message) {
        super(message);
    }

    // public UsernameNotFoundException(String username) {
    //     super("Username: " + username + " not found");
    // }

}
