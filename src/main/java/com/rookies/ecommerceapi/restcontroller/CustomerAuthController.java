package com.rookies.ecommerceapi.restcontroller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.payload.request.SignupRequest;
import com.rookies.ecommerceapi.service.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/customer/auth")
@Tag(name = "CUSTOMER", description = "CUSTOMER API")
public class CustomerAuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAuthController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerAuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Customer sign in", description = "", tags = { "CUSTOMER" })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(customerService.authenticateCustomer(loginRequest));
    }

    @Operation(summary = "Customer sign up", description = "", tags = { "CUSTOMER" })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> registerCustomer(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(customerService.registerCustomer(signUpRequest));
    }
}
