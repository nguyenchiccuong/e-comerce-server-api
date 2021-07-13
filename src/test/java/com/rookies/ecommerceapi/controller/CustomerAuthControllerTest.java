package com.rookies.ecommerceapi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rookies.ecommerceapi.entity.Customer;
import com.rookies.ecommerceapi.entity.Role;
import com.rookies.ecommerceapi.entity.RoleName;
import com.rookies.ecommerceapi.entity.User;
import com.rookies.ecommerceapi.repository.CustomerRepository;
import com.rookies.ecommerceapi.repository.RoleRepository;
import com.rookies.ecommerceapi.repository.UserRepository;
import com.rookies.ecommerceapi.security.jwt.JwtUtils;
import com.rookies.ecommerceapi.security.service.impl.UserDetailsServiceImpl;
import com.rookies.ecommerceapi.service.CustomerService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CustomerAuthControllerTest {
        public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

        @Autowired
        private MockMvc mockMvc;

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

        @MockBean
        private CustomerService customerService;

        private Optional<User> user;

        private Role role;

        private Long userId = (long) 100;

        private Short roleId = 1;

        private Short status = 1;

        @BeforeEach
        void setUp() {
                this.role = new Role(roleId, RoleName.ROLE_CUSTOMER, null);
                this.user = Optional.of(new User(userId, "custom10",
                                "$2a$10$T0mqEMRz8NMJrSLyMRKQ3e5CySa./Nznslffh/iGm3PDxx1sDHZNy", this.role, status, null,
                                null, null, null));
        }

        @Test
        // @WithMockUser(username = "admin"/* , roles={"",""} */)
        public void authenticateCustomerTest() throws Exception {

                when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);

                String signInRequest = "{\"username\": \"custom10\", \"password\": \"Qwert1!@\"}";
                this.mockMvc.perform(
                                post("/customer/auth/signin").contentType(APPLICATION_JSON_UTF8).content(signInRequest))
                                .andExpect(status().is(200));
        }

        @Test
        public void registerCustomerTest() throws Exception {

                when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
                when(customerRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
                when(customerRepository.existsByPhoneNumber(Mockito.anyString())).thenReturn(true);
                when(roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER)).thenReturn(Optional.empty());
                when(userRepository.save(Mockito.any(User.class))).thenReturn(user.get());
                when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(null);

                String signUpRequest = "{\"username\": \"custom10\", \"password\": \"Qwert1!@\", \"name\": \"Test\"}";
                this.mockMvc.perform(
                                post("/customer/auth/signup").contentType(APPLICATION_JSON_UTF8).content(signUpRequest))
                                .andExpect(status().is(200));
        }
}
