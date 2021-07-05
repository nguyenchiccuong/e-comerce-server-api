package com.rookies.ecommerceapi.service;

import org.springframework.http.ResponseEntity;

import com.rookies.ecommerceapi.payload.request.LoginRequest;

public interface EmployeeService {
    public ResponseEntity<?> authenticateEmployee(LoginRequest loginRequest);
}
