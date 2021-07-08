package com.rookies.ecommerceapi.exception;

public class ProductIdNotFoundException extends RuntimeException{

    public ProductIdNotFoundException(Long id) {
        super("Product id: " + id + " not found");
    }
    
}
