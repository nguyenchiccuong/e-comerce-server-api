package com.rookies.ecommerceapi.exception;

public class ProductDetailIdExistInOrderDetail extends RuntimeException {

    public ProductDetailIdExistInOrderDetail(String message) {
        super(message);
    }

    // public ProductDetailIdExistInOrderDetail(Long productDetailId) {
    //     super("Product detail id: " + productDetailId + "exist in order detail");
    // }

}
