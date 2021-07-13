package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.rookies.ecommerceapi.converter.ProductConverter;
import com.rookies.ecommerceapi.dto.ProductDto;
import com.rookies.ecommerceapi.entity.Brand;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.entity.Origin;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/product")
public class ProductManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductManageController.class);

    private final ProductService productService;

    private final ModelMapper modelMapper;

    private final ProductConverter productConverter;

    @Autowired
    public ProductManageController(ProductService productService, ModelMapper modelMapper,
            ProductConverter productConverter) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.productConverter = productConverter;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ProductDto saveProduct(@Valid @RequestBody ProductDto productDto) {
        Product productRequest = productConverter.convertToEntity(productDto);
        Product product = productService.saveProduct(productRequest);
        return modelMapper.map(product, ProductDto.class);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDto productDto) {
        Product productRequest = productConverter.convertToEntity(productDto);
        return productService.updateProduct(productRequest);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long productId) {
        return productService.deleteProduct(productId);
    }
}
