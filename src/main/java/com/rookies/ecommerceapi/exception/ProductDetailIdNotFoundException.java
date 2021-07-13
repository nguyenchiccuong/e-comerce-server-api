package com.rookies.ecommerceapi.exception;

public class ProductDetailIdNotFoundException extends RuntimeException {

    public ProductDetailIdNotFoundException(String message) {
        super(message);
    }

    // public ProductDetailIdNotFoundException(Long productDetailId) {
    //     super("Product detail id: " + productDetailId + " not found");
    // }

}
