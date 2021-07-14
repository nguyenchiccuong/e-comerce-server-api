package com.rookies.ecommerceapi.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.BrandService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/brand")
public class BrandControler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandControler.class);

    private final BrandService brandService;

    @Autowired
    public BrandControler(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> retrieveBrands() {
        return ResponseEntity.ok(brandService.retrieveBrands());
    }

}
