package com.rookies.ecommerceapi.service;

import org.springframework.data.domain.Pageable;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.payload.request.SignupRequest;

public interface CustomerService {
    public ResponseDto authenticateCustomer(LoginRequest loginRequest);

    public ResponseDto registerCustomer(SignupRequest signUpRequest);

    public ResponseDto retrieveCustomers(Pageable page);

    public ResponseDto retrieveCustomerByUserId(Long userId);

    public ResponseDto lockCustomerByUserId(Long userId);

    public ResponseDto unlockCustomerByUserId(Long userId);

    public ResponseDto countCustomer();

    public ResponseDto countCustomerByStatus(Short status);

    public ResponseDto retrieveCustomersByStatus(Pageable page, Short status);

    public ResponseDto searchCustomers(String keyword, Pageable page);
}
