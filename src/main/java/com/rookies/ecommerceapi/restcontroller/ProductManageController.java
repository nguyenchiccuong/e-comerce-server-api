package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
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

    private final ProductService productService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductManageController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ProductDto saveProduct(@Valid @RequestBody ProductDto productDto) {
        Product productRequest = modelMapper.map(productDto, Product.class);
        productRequest.setBrand(new Brand());
        productRequest.setOrigin(new Origin());
        productRequest.setCategory(new Category());
        productRequest.getBrand().setId(productDto.getBrandId());
        productRequest.getOrigin().setId(productDto.getOriginId());
        productRequest.getCategory().setId(productDto.getCategoryId());
        Product product = productService.saveProduct(productRequest);
        return modelMapper.map(product, ProductDto.class);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDto productDto) {
        Product productRequest = modelMapper.map(productDto, Product.class);
        productRequest.setBrand(new Brand());
        productRequest.setOrigin(new Origin());
        productRequest.setCategory(new Category());
        productRequest.getBrand().setId(productDto.getBrandId());
        productRequest.getOrigin().setId(productDto.getOriginId());
        productRequest.getCategory().setId(productDto.getCategoryId());
        return productService.updateProduct(productRequest);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long productId) {
        return productService.deleteProduct(productId);
    }
}
