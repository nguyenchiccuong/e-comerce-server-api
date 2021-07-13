package com.rookies.ecommerceapi.exception;

public class ProductIdNotFoundException extends RuntimeException{

    public ProductIdNotFoundException(String message) {
        super(message);
    }

    // public ProductIdNotFoundException(Long id) {
    //     super("Product id: " + id + " not found");
    // }
    
}
