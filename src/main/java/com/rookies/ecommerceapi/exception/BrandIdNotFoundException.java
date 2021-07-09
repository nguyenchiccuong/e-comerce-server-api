package com.rookies.ecommerceapi.exception;

public class BrandIdNotFoundException extends RuntimeException {

    public BrandIdNotFoundException(Integer brandId) {
        super("Brand id: " + brandId + " not found");
    }

}
