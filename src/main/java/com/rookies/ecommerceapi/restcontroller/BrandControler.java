package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.List;

import com.rookies.ecommerceapi.dto.BrandDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.BrandService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/brand")
public class BrandControler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandControler.class);

    private final BrandService brandService;

    private final ModelMapper modelMapper;

    @Autowired
    public BrandControler(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> retrieveBrands() {
        return ResponseEntity.ok(brandService.retrieveBrands());
    }

}
