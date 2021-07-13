package com.rookies.ecommerceapi.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rookies.ecommerceapi.constant.SuccessCode;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Customer;
import com.rookies.ecommerceapi.entity.Role;
import com.rookies.ecommerceapi.entity.RoleName;
import com.rookies.ecommerceapi.entity.User;
import com.rookies.ecommerceapi.payload.request.SignupRequest;
import com.rookies.ecommerceapi.payload.respone.MessageResponse;
import com.rookies.ecommerceapi.repository.CustomerRepository;
import com.rookies.ecommerceapi.repository.RoleRepository;
import com.rookies.ecommerceapi.repository.UserRepository;
import com.rookies.ecommerceapi.security.jwt.JwtUtils;
import com.rookies.ecommerceapi.security.service.impl.UserDetailsServiceImpl;
import com.rookies.ecommerceapi.service.CustomerService;
import com.rookies.ecommerceapi.service.impl.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private PasswordEncoder encoder;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private CustomerService customerService;

    private Optional<User> user;

    private Optional<Customer> customer;

    private Role role;

    private Long userId = (long) 100;

    private Short roleId = 1;

    private Short status = 1;

    @BeforeEach
    public void setUp() {
        this.role = new Role(roleId, RoleName.ROLE_CUSTOMER, null);

        this.user = Optional
                .of(new User(userId, "custom10", "$2a$10$T0mqEMRz8NMJrSLyMRKQ3e5CySa./Nznslffh/iGm3PDxx1sDHZNy",
                        this.role, status, null, null, null, null));

        this.customer = Optional.of(new Customer(userId, user.get(), "test", "0944200325", null, "test12@gmail.com",
                null, false, LocalDateTime.now()));
    }

    @Test
    public void registerCustomerTest() {
        // Given

        SignupRequest signUpRequest = new SignupRequest("custom10", "Qwert1!@", null, "test", "0944200325",
                "test12@gmail.com");

        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
        when(customerRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(false);
        when(customerRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())).thenReturn(false);
        when(roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER)).thenReturn(Optional.of(role));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(this.user.get());
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(this.customer.get());

        // When
        ResponseDto responseDto = customerService.registerCustomer(signUpRequest);

        // Then
        ResponseDto responseDtoExpect = new ResponseDto();
        responseDtoExpect.setSuccessCode(SuccessCode.SUCCESS);
        assertEquals(responseDtoExpect, responseDto);
    }

    @Test
    public void retrieveCustomerByUserIdTest() {
        // Given
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(this.customer);

        // When
        ResponseDto responseDto = customerService.retrieveCustomerByUserId(Mockito.anyLong());

        // Then
        ResponseDto responseDtoExpect = new ResponseDto();
        responseDtoExpect.setSuccessCode(SuccessCode.SUCCESS);
        responseDtoExpect.setData(this.customer.get());
        assertEquals(responseDtoExpect, responseDto);
    }

}
