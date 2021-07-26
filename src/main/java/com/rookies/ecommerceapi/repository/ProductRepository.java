package com.rookies.ecommerceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.rookies.ecommerceapi.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query("FROM Product p WHERE p.category.id = ?1")
    List<Product> findByCategoryId(Integer categoryId);

    @Query("SELECT COUNT (*) FROM Product p WHERE p.category.id = ?1")
    public Long countByCategoryId(Integer categoryId);

    @Query("SELECT COUNT (*) FROM Product p WHERE p.category.category.id = ?1")
    public Long countByParentCategoryId(Integer categoryId);

    @Query("SELECT COUNT (*) FROM Product p WHERE p.brand.brandName like %?1%")
    public Long countByBrandName(String brandName);

    @Query("SELECT COUNT (*) FROM Product p WHERE p.origin.country like %?1%")
    public Long countByCountry(String country);

    @Query("FROM Product p WHERE p.category.id = ?1")
    Page<Product> findByCategoryId(Pageable page, Integer categoryId);

    @Query("FROM Product p WHERE p.brand.brandName like %?1%")
    Page<Product> findByBrandName(Pageable page, String brandName);

    @Query("FROM Product p WHERE p.origin.country like %?1%")
    Page<Product> findByCountry(Pageable page, String country);

    @Query("FROM Product p WHERE p.category.category.id = ?1")
    Page<Product> findByParentCategoryId(Pageable page, Integer categoryId);
}
