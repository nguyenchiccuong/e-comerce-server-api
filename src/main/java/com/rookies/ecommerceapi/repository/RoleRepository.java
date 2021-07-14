package com.rookies.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.rookies.ecommerceapi.entity.Role;
import com.rookies.ecommerceapi.entity.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short>{
    Optional<Role> findByRoleName(RoleName roleName); 
}
