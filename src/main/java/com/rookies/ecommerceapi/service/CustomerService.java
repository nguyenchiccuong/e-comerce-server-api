package com.rookies.ecommerceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.rookies.ecommerceapi.entity.Customer;
import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.payload.request.SignupRequest;

public interface CustomerService {
    public ResponseEntity<?> authenticateCustomer(LoginRequest loginRequest);

    public ResponseEntity<?> registerCustomer(SignupRequest signUpRequest);

    public Page<Customer> retrieveCustomers(Pageable page);

    public Customer retrieveCustomerByUserId(Long userId);

    public ResponseEntity<?> lockCustomerByUserId(Long userId);

    public ResponseEntity<?> unlockCustomerByUserId(Long userId);
}
