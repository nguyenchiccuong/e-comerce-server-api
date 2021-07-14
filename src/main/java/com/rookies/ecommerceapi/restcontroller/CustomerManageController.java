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

import com.rookies.ecommerceapi.dto.CustomerDto;
import com.rookies.ecommerceapi.dto.NumberOfEntityDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto> retrieveCustomers(@RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems) {
        return ResponseEntity.ok(
                customerService.retrieveCustomers(PageRequest.of(pageNum, numOfItems, Sort.by("userId").descending())));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> retrieveCustomerByUserId(@PathVariable("userId") Long userId) {
        ResponseDto responseDto = customerService.retrieveCustomerByUserId(userId);

        CustomerDto customerDto = modelMapper.map(responseDto.getData(), CustomerDto.class);
        responseDto.setData(customerDto);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/lock/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> lockCustomerByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(customerService.lockCustomerByUserId(userId));
    }

    @PutMapping("/unlock/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> unlockCustomerByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(customerService.unlockCustomerByUserId(userId));
    }

    @GetMapping("/status")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> retrieveCustomersByStatus(
            @RequestParam(name = "page", required = true) Integer pageNum,
            @RequestParam(name = "items", required = true) Integer numOfItems,
            @RequestParam(name = "status", required = true) Short status) {
        return ResponseEntity.ok(customerService.retrieveCustomersByStatus(
                PageRequest.of(pageNum, numOfItems, Sort.by("userId").descending()), status));
    }

    @GetMapping("/count/status")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> countCustomerByStatus(
            @RequestParam(name = "status", required = true) Short status) {
        ResponseDto responseDto = customerService.countCustomerByStatus(status);

        NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
        responseDto.setData(quantity);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> countCustomer() {
        ResponseDto responseDto = customerService.countCustomer();

        NumberOfEntityDto quantity = new NumberOfEntityDto((Long) responseDto.getData());
        responseDto.setData(quantity);

        return ResponseEntity.ok(responseDto);
    }

}
