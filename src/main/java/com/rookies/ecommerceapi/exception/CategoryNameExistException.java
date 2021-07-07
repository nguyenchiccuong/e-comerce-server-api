package com.rookies.ecommerceapi.exception;

public class CategoryNameExistException extends RuntimeException {

    public CategoryNameExistException(String categoryName) {
        super("Category name: " + categoryName + " exist ");
    }

}