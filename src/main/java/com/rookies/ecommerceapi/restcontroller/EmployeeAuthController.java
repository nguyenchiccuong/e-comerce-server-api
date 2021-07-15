package com.rookies.ecommerceapi.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.validation.Valid;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.service.EmployeeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/auth")
@Tag(name = "EMPLOYEE", description = "EMPLOYEE API")
public class EmployeeAuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAuthController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeAuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Employee sign in", description = "", tags = { "EMPLOYEE" })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> authenticateEmployee(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(employeeService.authenticateEmployee(loginRequest));
    }
}
