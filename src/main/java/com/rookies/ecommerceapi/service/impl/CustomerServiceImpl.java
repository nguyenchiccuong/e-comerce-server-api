package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import java.time.LocalDateTime;

import com.rookies.ecommerceapi.dto.CustomerDto;
import com.rookies.ecommerceapi.entity.Customer;
import com.rookies.ecommerceapi.entity.Role;
import com.rookies.ecommerceapi.entity.RoleName;
import com.rookies.ecommerceapi.entity.User;
import com.rookies.ecommerceapi.exception.RoleNameNotFoundException;
import com.rookies.ecommerceapi.exception.UserIdNotFoundException;
import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.payload.request.SignupRequest;
import com.rookies.ecommerceapi.payload.respone.JwtResponse;
import com.rookies.ecommerceapi.payload.respone.MessageResponse;
import com.rookies.ecommerceapi.repository.CustomerRepository;
import com.rookies.ecommerceapi.repository.RoleRepository;
import com.rookies.ecommerceapi.repository.UserRepository;
import com.rookies.ecommerceapi.security.jwt.JwtUtils;
import com.rookies.ecommerceapi.security.service.impl.UserDetailsImpl;
import com.rookies.ecommerceapi.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CustomerRepository customerRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @Autowired
    public CustomerServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, CustomerRepository customerRepository, PasswordEncoder encoder,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> authenticateCustomer(LoginRequest loginRequest) {
        // TODO, authenticate when login
        // Username, pass from client
        // com.nashtech.rookies.security.WebSecurityConfig.configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
        // authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // on this step, we tell to authenticationManager how we load data from database
        // and the password encoder
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // if go there, the user/password is correct
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate jwt to return to client
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles.get(0)));
    }

    @Override
    public ResponseEntity<?> registerCustomer(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (signUpRequest.getEmail() != null && !signUpRequest.getEmail().equals("")) {
            if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
            }
        }

        if (signUpRequest.getPhoneNumber() != null && !signUpRequest.getPhoneNumber().equals("")) {
            if (customerRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Phone number is already in use!"));
            }
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Role role = roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER)
                .orElseThrow(() -> new RoleNameNotFoundException(RoleName.ROLE_CUSTOMER.name()));
        user.setRole(role);

        Short status = 1;
        user.setStatus(status);

        user = userRepository.save(user);
        long id = user.getId();

        Customer customer = new Customer(id, signUpRequest.getName(), signUpRequest.getPhoneNumber(),
                signUpRequest.getEmail(), LocalDateTime.now());

        customer.setUser(user);

        customerRepository.save(customer);

        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }

    @Override
    public Page<Customer> retrieveCustomers(Pageable page) {
        return customerRepository.findAll(page);
    }

    @Override
    public Customer retrieveCustomerByUserId(Long userId) {
        Optional<Customer> customerByUserId = customerRepository.findByUserId(userId);
        if (!customerByUserId.isPresent()) {
            throw new UserIdNotFoundException(userId);
        }
        return customerRepository.findByUserId(userId).get();
    }

}
