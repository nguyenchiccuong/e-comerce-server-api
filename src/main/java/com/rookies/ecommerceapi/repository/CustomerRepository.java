package com.rookies.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.rookies.ecommerceapi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String email);

    Optional<Customer> findByUserId(Long id);

}
