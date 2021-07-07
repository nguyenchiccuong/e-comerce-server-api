package com.rookies.ecommerceapi.exception;

public class CategoryIdNotFoundException extends RuntimeException {
    public CategoryIdNotFoundException(Integer id) {
        super("Category id: " + id + " not found ");
    }
}
