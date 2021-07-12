package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import java.util.List;

import com.rookies.ecommerceapi.dto.NumberOfEntityDto;
import com.rookies.ecommerceapi.dto.ProductDto;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/product")
public class ProductController {

    private final ProductService productService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProductDto> retrieveProducts(@RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems) {
        return productService
                .retrieveProducts(PageRequest.of(pageNum, numOfItems,
                        Sort.by("id").descending().and(Sort.by("updateDate").descending())))
                .stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public ProductDto retrieveProductById(@PathVariable("productId") Long productId) {
        Product product = productService.retrieveProductById(productId);
        return modelMapper.map(product, ProductDto.class);
    }

    @GetMapping("/category")
    public List<ProductDto> retrieveProductsByCategoryId(
            @RequestParam(name = "category_id", required = true) Integer categoryId,
            @RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems) {

        return productService
                .retrieveProductsByCategoryId(PageRequest.of(pageNum, numOfItems,
                        Sort.by("id").descending().and(Sort.by("updateDate").descending())), categoryId)
                .stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/brand")
    public List<ProductDto> retrieveProductsByBrandName(
            @RequestParam(name = "brand_name", required = true) String brandName,
            @RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems) {
        return productService
                .retrieveProductsByBrandName(PageRequest.of(pageNum, numOfItems,
                        Sort.by("id").descending().and(Sort.by("updateDate").descending())), brandName)
                .stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/origin")
    public List<ProductDto> retrieveProductsByCountry(@RequestParam(name = "country", required = true) String country,
            @RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems) {
        return productService
                .retrieveProductsByCountry(PageRequest.of(pageNum, numOfItems,
                        Sort.by("id").descending().and(Sort.by("updateDate").descending())), country)
                .stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/count")
    public NumberOfEntityDto countProduct() {
        return new NumberOfEntityDto(productService.countProduct());
    }

    @GetMapping("/count/category")
    public NumberOfEntityDto countProductByCategoryId(
            @RequestParam(name = "category_id", required = true) Integer categoryId) {
        return new NumberOfEntityDto(productService.countProductByCategoryId(categoryId));
    }

    @GetMapping("/count/brand")
    public NumberOfEntityDto countProductByBrandName(
            @RequestParam(name = "brand_name", required = true) String brandName) {
        return new NumberOfEntityDto(productService.countProductByBrandName(brandName));
    }

    @GetMapping("/count/origin")
    public NumberOfEntityDto countProductByCountry(@RequestParam(name = "country", required = true) String country) {
        return new NumberOfEntityDto(productService.countProductByCountry(country));
    }

    // remeber to research valid only work when input or output

}
