package com.rookies.ecommerceapi.restcontroller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class CustomerAuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAuthController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerAuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(customerService.authenticateCustomer(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> registerCustomer(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(customerService.registerCustomer(signUpRequest));
    }
}
