package com.rookies.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.ecommerceapi.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
