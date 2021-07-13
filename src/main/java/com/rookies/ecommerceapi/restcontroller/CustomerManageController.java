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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import com.rookies.ecommerceapi.dto.CustomerDto;
import com.rookies.ecommerceapi.dto.NumberOfEntityDto;
import com.rookies.ecommerceapi.entity.Customer;
import com.rookies.ecommerceapi.service.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/customer")
public class CustomerManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerManageController.class);

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
        return customerService.retrieveCustomers(PageRequest.of(pageNum, numOfItems, Sort.by("userId").descending()))
                .stream().map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
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

    @GetMapping("/status")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public List<CustomerDto> retrieveCustomersByStatus(@RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems,
            @RequestParam(name = "status", required = true) Short status) {
        return customerService
                .retrieveCustomersByStatus(PageRequest.of(pageNum, numOfItems, Sort.by("userId").descending()), status)
                .stream().map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/count/status")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public NumberOfEntityDto countCustomerByStatus(@RequestParam(name = "status", required = true) Short status) {
        return new NumberOfEntityDto(customerService.countCustomerByStatus(status));
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public NumberOfEntityDto countCustomer() {
        return new NumberOfEntityDto(customerService.countCustomer());
    }

}
