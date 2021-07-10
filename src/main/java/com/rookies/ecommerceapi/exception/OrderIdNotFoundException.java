package com.rookies.ecommerceapi.exception;

public class OrderIdNotFoundException extends RuntimeException {

    public OrderIdNotFoundException(Long orderId) {
        super("Order id: " + orderId + " not found");
    }

}
