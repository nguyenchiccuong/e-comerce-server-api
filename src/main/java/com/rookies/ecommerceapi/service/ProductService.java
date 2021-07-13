package com.rookies.ecommerceapi.service;

import org.springframework.data.domain.Pageable;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Product;

public interface ProductService {

    public ResponseDto retrieveProducts(Pageable page);

    public ResponseDto retrieveProductById(Long id);

    public ResponseDto retrieveProductsByCategoryId(Pageable page, Integer categoryId);

    public ResponseDto retrieveProductsByBrandName(Pageable page, String brandName);

    public ResponseDto retrieveProductsByCountry(Pageable page, String country);

    public ResponseDto countProduct();

    public ResponseDto countProductByCategoryId(Integer categoryId);

    public ResponseDto countProductByBrandName(String brandName);

    public ResponseDto countProductByCountry(String country);

    public ResponseDto saveProduct(Product product);

    public ResponseDto updateProduct(Product product);

    public ResponseDto deleteProduct(Long productId);
}
