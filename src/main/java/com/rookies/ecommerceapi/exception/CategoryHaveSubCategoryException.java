package com.rookies.ecommerceapi.exception;

public class CategoryHaveSubCategoryException extends RuntimeException {

    public CategoryHaveSubCategoryException(String message) {
        super(message);
    }

    // public CategoryHaveSubCategoryException(String categoryName) {
    //     super("Category: " + categoryName + " already have sub category, cannot assign to a product");
    // }

}
