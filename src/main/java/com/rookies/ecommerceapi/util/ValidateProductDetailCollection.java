package com.rookies.ecommerceapi.util;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.rookies.ecommerceapi.entity.ProductDetail;

public class ValidateProductDetailCollection {
    public static Boolean validateProductDetailCollection(Collection<ProductDetail> productDetails) {
        Set<String> setOfColor = productDetails.stream().map(productDetail -> productDetail.getColor())
                .collect(Collectors.toSet());
        return setOfColor.size() < productDetails.size() ? false : true;
    }
}
