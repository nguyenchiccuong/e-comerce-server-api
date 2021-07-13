package com.rookies.ecommerceapi.exception;

public class ProductDetailNotValidException extends RuntimeException {

    public ProductDetailNotValidException(String message) {
        super(message);
    }

    // public ProductDetailNotValidException() {
    //     super("Product detail duplicate color");
    // }

}
