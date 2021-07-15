package com.rookies.ecommerceapi.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.BrandService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/brand")
@Tag(name = "BRAND", description = "BRAND API")
public class BrandControler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandControler.class);

    private final BrandService brandService;

    @Autowired
    public BrandControler(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "Get all brand", description = "", tags = { "BRAND" })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @GetMapping
    public ResponseEntity<ResponseDto> retrieveBrands() {
        return ResponseEntity.ok(brandService.retrieveBrands());
    }

}
