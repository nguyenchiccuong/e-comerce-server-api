package com.rookies.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.ecommerceapi.entity.Origin;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Integer>{
    
}
