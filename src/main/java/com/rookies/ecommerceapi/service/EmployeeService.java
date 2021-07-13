package com.rookies.ecommerceapi.service;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.payload.request.LoginRequest;

public interface EmployeeService {
    public ResponseDto authenticateEmployee(LoginRequest loginRequest);
}
