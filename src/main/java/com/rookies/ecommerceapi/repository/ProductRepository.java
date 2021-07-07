package com.rookies.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.rookies.ecommerceapi.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("FROM Product p WHERE p.category.id = ?1")
    List<Product> findByCategoryId(Integer categoryId);
}
