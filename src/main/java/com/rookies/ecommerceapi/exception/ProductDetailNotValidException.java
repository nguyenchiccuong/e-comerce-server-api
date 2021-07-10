package com.rookies.ecommerceapi.exception;

public class ProductDetailNotValidException extends RuntimeException {

    public ProductDetailNotValidException() {
        super("Product detail duplicate color");
    }

}
