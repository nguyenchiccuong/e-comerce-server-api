package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.rookies.ecommerceapi.dto.NumberOfEntityDto;
import com.rookies.ecommerceapi.dto.ProductDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/product")
@Tag(name = "PRODUCT", description = "PRODUCT API")
public class ProductController {

        private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

        private final ProductService productService;

        private final ModelMapper modelMapper;

        @Autowired
        public ProductController(ProductService productService, ModelMapper modelMapper) {
                this.productService = productService;
                this.modelMapper = modelMapper;
        }

        @Operation(summary = "Get all product", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
        @GetMapping
        public ResponseEntity<ResponseDto> retrieveProducts(
                        @RequestParam(name = "page", required = true) Integer pageNum,
                        @RequestParam(name = "items", required = true) Integer numOfItems) {
                return ResponseEntity.ok(productService.retrieveProducts(PageRequest.of(pageNum, numOfItems,
                                Sort.by("id").descending().and(Sort.by("updateDate").descending()))));
        }

        @Operation(summary = "Get product by id", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
        @GetMapping("/{productId}")
        public ResponseEntity<ResponseDto> retrieveProductById(@PathVariable("productId") Long productId) {
                ResponseDto responseDto = productService.retrieveProductById(productId);

                ProductDto productByIdDto = modelMapper.map(responseDto.getData(), ProductDto.class);
                responseDto.setData(productByIdDto);

                return ResponseEntity.ok(responseDto);

        }

        @Operation(summary = "Get all product by category id", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
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

        @Operation(summary = "Get all product by brand name", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
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

        @Operation(summary = "Get all product by country", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
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

        @Operation(summary = "Count product", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
        @GetMapping("/count")
        public ResponseEntity<ResponseDto> countProduct() {
                ResponseDto responseDto = productService.countProduct();

                NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
                responseDto.setData(quantity);

                return ResponseEntity.ok(responseDto);
        }

        @Operation(summary = "Count product by category id", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
        @GetMapping("/count/category")
        public ResponseEntity<ResponseDto> countProductByCategoryId(
                        @RequestParam(name = "category_id", required = true) Integer categoryId) {
                ResponseDto responseDto = productService.countProductByCategoryId(categoryId);

                NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
                responseDto.setData(quantity);

                return ResponseEntity.ok(responseDto);
        }

        @Operation(summary = "Count product by brand name", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
        @GetMapping("/count/brand")
        public ResponseEntity<ResponseDto> countProductByBrandName(
                        @RequestParam(name = "brand_name", required = true) String brandName) {
                ResponseDto responseDto = productService.countProductByBrandName(brandName);

                NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
                responseDto.setData(quantity);

                return ResponseEntity.ok(responseDto);
        }

        @Operation(summary = "Count product by country", description = "", tags = { "PRODUCT" })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
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
