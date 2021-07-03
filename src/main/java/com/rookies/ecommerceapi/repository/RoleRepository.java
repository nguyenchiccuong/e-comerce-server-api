package com.rookies.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.ecommerceapi.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short>{
    
}
