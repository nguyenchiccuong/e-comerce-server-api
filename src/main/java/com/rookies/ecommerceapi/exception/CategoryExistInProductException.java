package com.rookies.ecommerceapi.exception;

public class CategoryExistInProductException extends RuntimeException {

    public CategoryExistInProductException(String message) {
        super(message);
    }

}
