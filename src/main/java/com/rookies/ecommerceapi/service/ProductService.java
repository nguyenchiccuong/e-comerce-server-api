package com.rookies.ecommerceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.rookies.ecommerceapi.entity.Product;

public interface ProductService {

    public Page<Product> retrieveProducts(Pageable page);

    public Product retrieveProductById(Long id);

    public Page<Product> retrieveProductsByCategoryId(Pageable page, Integer categoryId);

    public Page<Product> retrieveProductsByBrandName(Pageable page, String brandName);

    public Page<Product> retrieveProductsByCountry(Pageable page, String country);

    public Long countProduct();

    public Long countProductByCategoryId(Integer categoryId);

    public Long countProductByBrandName(String brandName);

    public Long countProductByCountry(String country);

    public Product saveProduct(Product product);

    public ResponseEntity<?> updateProduct(Product product);

    public ResponseEntity<?> deleteProduct(Long productId);
}
