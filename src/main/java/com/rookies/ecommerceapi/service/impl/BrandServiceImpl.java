package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.rookies.ecommerceapi.entity.Brand;
import com.rookies.ecommerceapi.service.BrandService;
import com.rookies.ecommerceapi.repository.BrandRepository;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> retrieveBrands() {
        return brandRepository.findAll();
    }

}
