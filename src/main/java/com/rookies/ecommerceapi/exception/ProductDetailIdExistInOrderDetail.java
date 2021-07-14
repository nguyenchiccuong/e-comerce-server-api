package com.rookies.ecommerceapi.exception;

public class ProductDetailIdExistInOrderDetail extends RuntimeException {

    public ProductDetailIdExistInOrderDetail(String message) {
        super(message);
    }

}
