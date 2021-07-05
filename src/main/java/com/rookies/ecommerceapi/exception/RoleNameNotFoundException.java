package com.rookies.ecommerceapi.exception;

public class RoleNameNotFoundException extends RuntimeException {

    public RoleNameNotFoundException(String roleName) {
        super("Error: Role: " + roleName + " is not found.");
    }

}