package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.rookies.ecommerceapi.dto.CustomerDto;
import com.rookies.ecommerceapi.entity.Customer;
import com.rookies.ecommerceapi.service.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/employee/customer")
public class CustomerManageController {
    private final CustomerService customerService;

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerManageController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public List<CustomerDto> retrieveCustomers(@RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems) {
        return customerService.retrieveCustomers(PageRequest.of(pageNum, numOfItems)).stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public CustomerDto retrieveCustomerByUserId(@PathVariable("userId") Long userId) {
        Customer customer = customerService.retrieveCustomerByUserId(userId);
        return modelMapper.map(customer, CustomerDto.class);
    }

    @PutMapping("/lock/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> lockCustomerByUserId(@PathVariable("userId") Long userId) {
        return customerService.lockCustomerByUserId(userId);
    }

    @PutMapping("/unlock/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> unlockCustomerByUserId(@PathVariable("userId") Long userId) {
        return customerService.unlockCustomerByUserId(userId);
    }

}
