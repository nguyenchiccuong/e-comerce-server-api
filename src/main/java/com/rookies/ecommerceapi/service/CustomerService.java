package com.rookies.ecommerceapi.service;

import org.springframework.http.ResponseEntity;

import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.payload.request.SignupRequest;

public interface CustomerService {
    public ResponseEntity<?> authenticateCustomer(LoginRequest loginRequest);

    public ResponseEntity<?> registerCustomer(SignupRequest signUpRequest);
}
