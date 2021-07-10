package com.rookies.ecommerceapi.exception;

public class ProductDetailIdExistInOrderDetail extends RuntimeException {

    public ProductDetailIdExistInOrderDetail(Long productDetailId) {
        super("Product detail id: " + productDetailId + "exist in order detail");
    }

}
