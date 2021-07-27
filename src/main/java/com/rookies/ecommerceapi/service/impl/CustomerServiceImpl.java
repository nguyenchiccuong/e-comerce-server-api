package com.rookies.ecommerceapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import com.rookies.ecommerceapi.constant.ErrorCode;
import com.rookies.ecommerceapi.constant.SuccessCode;
import com.rookies.ecommerceapi.dto.CustomerDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Customer;
import com.rookies.ecommerceapi.entity.Role;
import com.rookies.ecommerceapi.entity.RoleName;
import com.rookies.ecommerceapi.entity.User;
import com.rookies.ecommerceapi.exception.RoleNameNotFoundException;
import com.rookies.ecommerceapi.exception.SaveErrorException;
import com.rookies.ecommerceapi.exception.UserIdNotFoundException;
import com.rookies.ecommerceapi.exception.UsernameAlreadyTakenException;
import com.rookies.ecommerceapi.exception.PhonenumberAlreadyTakenException;
import com.rookies.ecommerceapi.exception.EmailAlreadyTakenException;
import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.payload.request.SignupRequest;
import com.rookies.ecommerceapi.payload.respone.JwtResponse;
import com.rookies.ecommerceapi.repository.CustomerRepository;
import com.rookies.ecommerceapi.repository.RoleRepository;
import com.rookies.ecommerceapi.repository.UserRepository;
import com.rookies.ecommerceapi.repository.specs.CustomerSpecification;
import com.rookies.ecommerceapi.repository.specs.SearchCriteria;
import com.rookies.ecommerceapi.repository.specs.SearchOperation;
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

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, CustomerRepository customerRepository, PasswordEncoder encoder,
            JwtUtils jwtUtils, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto authenticateCustomer(LoginRequest loginRequest) {
        ResponseDto responseDto = new ResponseDto();

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

        responseDto.setSuccessCode(SuccessCode.SUCCESS_CUSTOMER_LOGIN);
        responseDto.setData(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles.get(0)));
        return responseDto;
    }

    @Override
    public ResponseDto registerCustomer(SignupRequest signUpRequest) {
        ResponseDto responseDto = new ResponseDto();

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameAlreadyTakenException(ErrorCode.ERR_USERNAME_ALREADY_TAKEN);
        }

        if (signUpRequest.getEmail() != null && !signUpRequest.getEmail().equals("")) {
            if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
                throw new EmailAlreadyTakenException(ErrorCode.ERR_EMAIL_ALREADY_TAKEN);
            }
        }

        if (signUpRequest.getPhoneNumber() != null && !signUpRequest.getPhoneNumber().equals("")) {
            if (customerRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
                throw new PhonenumberAlreadyTakenException(ErrorCode.ERR_PHONENUMBER_ALREADY_TAKEN);
            }
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Role role = roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER)
                .orElseThrow(() -> new RoleNameNotFoundException(RoleName.ROLE_CUSTOMER.name()));
        user.setRole(role);

        Short status = 1;
        user.setStatus(status);

        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveErrorException(ErrorCode.ERR_SAVE_USER);
        }
        long id = user.getId();

        Customer customer = new Customer(id, signUpRequest.getName(), signUpRequest.getPhoneNumber(),
                signUpRequest.getEmail(), LocalDateTime.now());

        customer.setUser(user);

        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveErrorException(ErrorCode.ERR_SAVE_CUSTOMER);
        }

        responseDto.setSuccessCode(SuccessCode.SUCCESS_CUSTOMER_SIGN_UP);
        return responseDto;
    }

    @Override
    public ResponseDto retrieveCustomers(Pageable page) {
        ResponseDto responseDto = new ResponseDto();
        Page<Customer> customers = customerRepository.findAll(page);
        List<CustomerDto> customersDto = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
        responseDto.setData(customersDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS_GET_ALL_CUSTOMER);
        return responseDto;
    }

    @Override
    public ResponseDto retrieveCustomerByUserId(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        Customer customer = customerRepository.findByUserId(userId)
                .orElseThrow(() -> new UserIdNotFoundException(ErrorCode.ERR_USER_ID_NOT_FOUND));

        responseDto.setSuccessCode(SuccessCode.SUCCESS_GET_CUSTOMER);
        responseDto.setData(customer);
        return responseDto;
    }

    @Override
    @Transactional
    public ResponseDto lockCustomerByUserId(Long userId) {
        ResponseDto responseDto = new ResponseDto();

        customerRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(ErrorCode.ERR_USER_ID_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(ErrorCode.ERR_USER_ID_NOT_FOUND));

        Role roleByRoleName = roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER_LOCKED)
                .orElseThrow(() -> new RoleNameNotFoundException(RoleName.ROLE_CUSTOMER_LOCKED.name()));

        user.setRole(roleByRoleName);
        Short status = 0;
        user.setStatus(status);

        responseDto.setSuccessCode(SuccessCode.SUCCESS_LOCK_CUSTOMER);
        return responseDto;
    }

    @Override
    @Transactional
    public ResponseDto unlockCustomerByUserId(Long userId) {
        ResponseDto responseDto = new ResponseDto();

        customerRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(ErrorCode.ERR_USER_ID_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(ErrorCode.ERR_USER_ID_NOT_FOUND));

        Role roleByRoleName = roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER)
                .orElseThrow(() -> new RoleNameNotFoundException(RoleName.ROLE_CUSTOMER.name()));

        user.setRole(roleByRoleName);
        Short status = 1;
        user.setStatus(status);

        responseDto.setSuccessCode(SuccessCode.SUCCESS_UNLOCK_CUSTOMER);
        return responseDto;
    }

    @Override
    public ResponseDto countCustomer() {
        ResponseDto responseDto = new ResponseDto();
        Long quantity = customerRepository.count();

        responseDto.setSuccessCode(SuccessCode.SUCCESS_COUNT_CUSTOMER);
        responseDto.setData(quantity);
        return responseDto;
    }

    @Override
    public ResponseDto countCustomerByStatus(Short status) {
        ResponseDto responseDto = new ResponseDto();
        Long quantity = customerRepository.countByStatus(status);

        responseDto.setSuccessCode(SuccessCode.SUCCESS_COUNT_CUSTOMER);
        responseDto.setData(quantity);
        return responseDto;
    }

    @Override
    public ResponseDto retrieveCustomersByStatus(Pageable page, Short status) {
        ResponseDto responseDto = new ResponseDto();
        Page<Customer> customersByStatus = customerRepository.findByStatus(page, status);
        List<CustomerDto> customerByStatusDto = customersByStatus.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
        responseDto.setData(customerByStatusDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS_GET_ALL_CUSTOMER);
        return responseDto;
    }

    @Override
    public ResponseDto searchCustomers(String keyword, Pageable page) {
        ResponseDto responseDto = new ResponseDto();

        CustomerSpecification customerName = new CustomerSpecification();
        customerName.add(new SearchCriteria("name", keyword, SearchOperation.MATCH));
        CustomerSpecification email = new CustomerSpecification();
        email.add(new SearchCriteria("email", keyword, SearchOperation.MATCH));
        CustomerSpecification phoneNumber = new CustomerSpecification();
        phoneNumber.add(new SearchCriteria("phoneNumber", keyword, SearchOperation.MATCH));
        Page<Customer> customers = customerRepository
                .findAll(Specification.where(customerName).or(email).or(phoneNumber), page);
        List<CustomerDto> customersDto = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());

        responseDto.setData(customersDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS_GET_ALL_CUSTOMER);
        return responseDto;
    }

}
