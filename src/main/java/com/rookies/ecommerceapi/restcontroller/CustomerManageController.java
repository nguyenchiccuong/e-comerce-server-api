package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.rookies.ecommerceapi.dto.CustomerDto;
import com.rookies.ecommerceapi.dto.NumberOfEntityDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/customer")
@Tag(name = "CUSTOMER", description = "CUSTOMER API")
public class CustomerManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerManageController.class);

    private final CustomerService customerService;

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerManageController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get all customer", description = "", tags = { "CUSTOMER" }, security = {
            @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> retrieveCustomers(@RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems) {
        return ResponseEntity.ok(
                customerService.retrieveCustomers(PageRequest.of(pageNum, numOfItems, Sort.by("name"))));
    }

    @Operation(summary = "Get customer by id", description = "", tags = { "CUSTOMER" }, security = {
            @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> retrieveCustomerByUserId(@PathVariable("userId") Long userId) {
        ResponseDto responseDto = customerService.retrieveCustomerByUserId(userId);

        CustomerDto customerDto = modelMapper.map(responseDto.getData(), CustomerDto.class);
        responseDto.setData(customerDto);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Lock customer by id", description = "", tags = { "CUSTOMER" }, security = {
            @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @PutMapping("/lock/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> lockCustomerByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(customerService.lockCustomerByUserId(userId));
    }

    @Operation(summary = "Unlock customer by id", description = "", tags = { "CUSTOMER" }, security = {
            @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @PutMapping("/unlock/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> unlockCustomerByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(customerService.unlockCustomerByUserId(userId));
    }

    @Operation(summary = "Get all customer by status", description = "", tags = { "CUSTOMER" }, security = {
            @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @GetMapping("/status")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> retrieveCustomersByStatus(
            @RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems,
            @RequestParam(name = "status", required = true) Short status) {
        return ResponseEntity.ok(customerService.retrieveCustomersByStatus(
                PageRequest.of(pageNum, numOfItems, Sort.by("name")), status));
    }

    @Operation(summary = "Count customer by status", description = "", tags = { "CUSTOMER" }, security = {
            @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @GetMapping("/count/status")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> countCustomerByStatus(
            @RequestParam(name = "status", required = true) Short status) {
        ResponseDto responseDto = customerService.countCustomerByStatus(status);

        NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
        responseDto.setData(quantity);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Count customer", description = "", tags = { "CUSTOMER" }, security = {
            @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @GetMapping("/count")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> countCustomer() {
        ResponseDto responseDto = customerService.countCustomer();

        NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
        responseDto.setData(quantity);

        return ResponseEntity.ok(responseDto);
    }

}
