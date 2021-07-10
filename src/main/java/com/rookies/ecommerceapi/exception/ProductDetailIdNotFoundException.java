package com.rookies.ecommerceapi.exception;

public class ProductDetailIdNotFoundException extends RuntimeException {

    public ProductDetailIdNotFoundException(Long productDetailId) {
        super("Product detail id: " + productDetailId + " not found");
    }

}
