package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.exception.ProductIdNotFoundException;
import com.rookies.ecommerceapi.repository.BrandRepository;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.repository.OriginRepository;
import com.rookies.ecommerceapi.repository.ProductRepository;
import com.rookies.ecommerceapi.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final OriginRepository originRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
            BrandRepository brandRepository, OriginRepository originRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.originRepository = originRepository;
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
    public Page<Product> retrieveProductsByCategoryId(Pageable page, Integer categoryId) {
        return productRepository.findByCategoryId(page, categoryId);
    }

    @Override
    public Page<Product> retrieveProductsByBrandName(Pageable page, String brandName) {
        return productRepository.findByBrandName(page, brandName);
    }

    @Override
    public Page<Product> retrieveProductsByCountry(Pageable page, String country) {
        return productRepository.findByCountry(page, country);
    }

    @Override
    public Long countProduct() {
        return productRepository.count();
    }

    @Override
    public Long countProductByCategoryId(Integer categoryId) {
        return productRepository.CountByCategoryId(categoryId);
    }

    @Override
    public Long countProductByBrandName(String brandName) {
        return productRepository.CountByBrandName(brandName);
    }

    @Override
    public Long countProductByCountry(String country) {
        return productRepository.CountByCountry(country);
    }
}
