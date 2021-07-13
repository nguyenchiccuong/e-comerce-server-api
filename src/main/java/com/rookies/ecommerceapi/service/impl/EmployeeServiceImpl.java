package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.rookies.ecommerceapi.constant.SuccessCode;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.payload.request.LoginRequest;
import com.rookies.ecommerceapi.payload.respone.JwtResponse;
import com.rookies.ecommerceapi.security.jwt.JwtUtils;
import com.rookies.ecommerceapi.security.service.impl.UserDetailsImpl;
import com.rookies.ecommerceapi.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Autowired
    public EmployeeServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseDto authenticateEmployee(LoginRequest loginRequest) {
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

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        responseDto.setData(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles.get(0)));
        return responseDto;
    }

}
