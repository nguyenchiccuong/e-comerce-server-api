package com.rookies.ecommerceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.rookies.ecommerceapi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String email);

    Optional<Customer> findByUserId(Long id);

    @Query("SELECT COUNT (*) FROM Customer c WHERE c.user.status = ?1")
    Long countByStatus(Short status);

    @Query("FROM Customer c WHERE c.user.status = ?1")
    Page<Customer> findByStatus(Pageable page, Short status);

}
