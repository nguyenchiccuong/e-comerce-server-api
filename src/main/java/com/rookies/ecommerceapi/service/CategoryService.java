package com.rookies.ecommerceapi.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.rookies.ecommerceapi.entity.Category;

public interface CategoryService {
    public List<Category> retrieveParentCategory();

    public List<Category> retrieveSubCategory(Integer parentId);

    public Category saveCategory(Category category);

    public Category saveSubCategory(Category category);

    public ResponseEntity<?> updateCategory(Category category);

    public ResponseEntity<?> deleteCategory(Integer id);
}
