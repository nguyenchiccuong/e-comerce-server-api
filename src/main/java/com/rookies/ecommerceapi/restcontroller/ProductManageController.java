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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;

import com.rookies.ecommerceapi.converter.ProductConverter;
import com.rookies.ecommerceapi.dto.ProductDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/product")
@Tag(name = "PRODUCT", description = "PRODUCT API")
public class ProductManageController {

        private static final Logger LOGGER = LoggerFactory.getLogger(ProductManageController.class);

        private final ProductService productService;

        private final ProductConverter productConverter;

        @Autowired
        public ProductManageController(ProductService productService, ProductConverter productConverter) {
                this.productService = productService;
                this.productConverter = productConverter;
        }

        @Operation(summary = "Save product", description = "", tags = { "PRODUCT" }, security = {
                        @SecurityRequirement(name = "bearer-key-employee"),
                        @SecurityRequirement(name = "bearer-key-manager"),
                        @SecurityRequirement(name = "bearer-key-admin") })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized"),
                        @ApiResponse(responseCode = "403", description = "Forbidden"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
        @PostMapping
        @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
        public ResponseEntity<ResponseDto> saveProduct(@Valid @RequestBody ProductDto productDto) {
                Product productRequest = productConverter.convertToEntity(productDto);

                ResponseDto responseDto = productService.saveProduct(productRequest);

                responseDto.setData(productConverter.convertToDto((Product) responseDto.getData()));

                return ResponseEntity.ok(responseDto);
        }

        @Operation(summary = "Update product", description = "", tags = { "PRODUCT" }, security = {
                        @SecurityRequirement(name = "bearer-key-manager"),
                        @SecurityRequirement(name = "bearer-key-admin") })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized"),
                        @ApiResponse(responseCode = "403", description = "Forbidden"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
        @PutMapping
        @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
        public ResponseEntity<ResponseDto> updateProduct(@Valid @RequestBody ProductDto productDto) {
                Product productRequest = productConverter.convertToEntity(productDto);
                return ResponseEntity.ok(productService.updateProduct(productRequest));
        }

        @Operation(summary = "Delete product", description = "", tags = { "PRODUCT" }, security = {
                        @SecurityRequirement(name = "bearer-key-admin") })
        @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized"),
                        @ApiResponse(responseCode = "403", description = "Forbidden"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error") })
        @DeleteMapping("/{productId}")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public ResponseEntity<ResponseDto> deleteProduct(@PathVariable("productId") Long productId) {
                return ResponseEntity.ok(productService.deleteProduct(productId));
        }
}
