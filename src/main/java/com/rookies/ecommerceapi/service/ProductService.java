package com.rookies.ecommerceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rookies.ecommerceapi.entity.Product;

public interface ProductService {

    public Page<Product> retrieveProducts(Pageable page);

    public Product retrieveProductById(Long id);

    public Page<Product> retrieveProductsByCategoryName(Pageable page);

    public Page<Product> retrieveProductsByBrandName(Pageable page);

    public Page<Product> retrieveProductsByOriginName(Pageable page);
}
