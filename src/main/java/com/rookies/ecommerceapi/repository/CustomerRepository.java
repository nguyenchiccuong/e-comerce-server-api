package com.rookies.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.ecommerceapi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
}
