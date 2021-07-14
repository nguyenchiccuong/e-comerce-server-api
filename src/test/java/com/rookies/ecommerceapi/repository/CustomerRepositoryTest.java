package com.rookies.ecommerceapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rookies.ecommerceapi.entity.Customer;
import com.rookies.ecommerceapi.entity.RoleName;
import com.rookies.ecommerceapi.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void CustomerTest() {
        User user = new User("user", "Qwert1!@");
        user.setStatus((short) 1);
        assertTrue(roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER).isPresent());
        user.setRole(roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER).get());
        assertNotNull(userRepository.save(user));

        user = userRepository.findByUsername("user").get();

        Customer customer = new Customer();
        customer.setCreateDate(LocalDateTime.now());
        customer.setUserId(user.getId());
        customer.setUser(user);
        customer.setName("name");
        assertNotNull(customerRepository.save(customer));

        userRepository.deleteById(user.getId());
        assertTrue(!userRepository.findByUsername("user").isPresent());
    }
}
