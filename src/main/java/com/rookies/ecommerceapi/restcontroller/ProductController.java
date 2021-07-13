package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
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
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/product")
public class ProductController {

        private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

        private final ProductService productService;

        private final ModelMapper modelMapper;

        @Autowired
        public ProductController(ProductService productService, ModelMapper modelMapper) {
                this.productService = productService;
                this.modelMapper = modelMapper;
        }

        @GetMapping
        public ResponseEntity<ResponseDto> retrieveProducts(
                        @RequestParam(name = "page", required = true) Integer pageNum,
                        @RequestParam(name = "items", required = true) Integer numOfItems) {
                return ResponseEntity.ok(productService.retrieveProducts(PageRequest.of(pageNum, numOfItems,
                                Sort.by("id").descending().and(Sort.by("updateDate").descending()))));
        }

        @GetMapping("/{productId}")
        public ResponseEntity<ResponseDto> retrieveProductById(@PathVariable("productId") Long productId) {
                ResponseDto responseDto = productService.retrieveProductById(productId);

                ProductDto productByIdDto = modelMapper.map(responseDto.getData(), ProductDto.class);
                responseDto.setData(productByIdDto);

                return ResponseEntity.ok(responseDto);

        }

        @GetMapping("/category")
        public ResponseEntity<ResponseDto> retrieveProductsByCategoryId(
                        @RequestParam(name = "category_id", required = true) Integer categoryId,
                        @RequestParam(name = "page", required = true) Integer pageNum,
                        @RequestParam(name = "items", required = true) Integer numOfItems) {
                return ResponseEntity
                                .ok(productService.retrieveProductsByCategoryId(
                                                PageRequest.of(pageNum, numOfItems,
                                                                Sort.by("id").descending().and(
                                                                                Sort.by("updateDate").descending())),
                                                categoryId));
        }

        @GetMapping("/brand")
        public ResponseEntity<ResponseDto> retrieveProductsByBrandName(
                        @RequestParam(name = "brand_name", required = true) String brandName,
                        @RequestParam(name = "page", required = true) Integer pageNum,
                        @RequestParam(name = "items", required = true) Integer numOfItems) {
                return ResponseEntity
                                .ok(productService.retrieveProductsByBrandName(
                                                PageRequest.of(pageNum, numOfItems,
                                                                Sort.by("id").descending().and(
                                                                                Sort.by("updateDate").descending())),
                                                brandName));
        }

        @GetMapping("/origin")
        public ResponseEntity<ResponseDto> retrieveProductsByCountry(
                        @RequestParam(name = "country", required = true) String country,
                        @RequestParam(name = "page", required = true) Integer pageNum,
                        @RequestParam(name = "items", required = true) Integer numOfItems) {
                return ResponseEntity
                                .ok(productService.retrieveProductsByCountry(
                                                PageRequest.of(pageNum, numOfItems,
                                                                Sort.by("id").descending().and(
                                                                                Sort.by("updateDate").descending())),
                                                country));
        }

        @GetMapping("/count")
        public ResponseEntity<ResponseDto> countProduct() {
                ResponseDto responseDto = productService.countProduct();

                NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
                responseDto.setData(quantity);

                return ResponseEntity.ok(responseDto);
        }

        @GetMapping("/count/category")
        public ResponseEntity<ResponseDto> countProductByCategoryId(
                        @RequestParam(name = "category_id", required = true) Integer categoryId) {
                ResponseDto responseDto = productService.countProductByCategoryId(categoryId);

                NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
                responseDto.setData(quantity);

                return ResponseEntity.ok(responseDto);
        }

        @GetMapping("/count/brand")
        public ResponseEntity<ResponseDto> countProductByBrandName(
                        @RequestParam(name = "brand_name", required = true) String brandName) {
                ResponseDto responseDto = productService.countProductByBrandName(brandName);

                NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
                responseDto.setData(quantity);

                return ResponseEntity.ok(responseDto);
        }

        @GetMapping("/count/origin")
        public ResponseEntity<ResponseDto> countProductByCountry(
                        @RequestParam(name = "country", required = true) String country) {
                ResponseDto responseDto = productService.countProductByCountry(country);

                NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
                responseDto.setData(quantity);

                return ResponseEntity.ok(responseDto);
        }

        // remeber to research valid only work when input or output

}
