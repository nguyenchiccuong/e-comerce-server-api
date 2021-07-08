package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.exception.ProductIdNotFoundException;
import com.rookies.ecommerceapi.repository.ProductRepository;
import com.rookies.ecommerceapi.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> retrieveProducts(Pageable page) {
        return productRepository.findAll(page);
    }

    @Override
    public Product retrieveProductById(Long id) {
        Optional<Product> productById = productRepository.findById(id);
        if (!productById.isPresent()) {
            throw new ProductIdNotFoundException(id);
        }

        return productById.get();
    }

    @Override
    public Page<Product> retrieveProductsByCategoryName(Pageable page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Product> retrieveProductsByBrandName(Pageable page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Product> retrieveProductsByOriginName(Pageable page) {
        // TODO Auto-generated method stub
        return null;
    }

}
