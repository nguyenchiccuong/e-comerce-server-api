package com.rookies.ecommerceapi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.service.EmployeeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/employee/auth")
public class EmployeeAuthController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeAuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateEmployee(@Valid @RequestBody LoginRequest loginRequest) {
        return employeeService.authenticateEmployee(loginRequest);
    }
}
