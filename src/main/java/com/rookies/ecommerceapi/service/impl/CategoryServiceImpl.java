package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.service.CategoryService;
import com.rookies.ecommerceapi.exception.CategoryNameExistException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> retrieveParentCategory() {
        return categoryRepository.findByCategoryIsNull();
    }

    @Override
    public List<Category> retrieveSubCategory(Integer parentId) {
        return categoryRepository.findSubCategoryByParentCategoryId(parentId);
    }

    @Override
    public Category saveCategory(Category category) {
        Optional<Category> categoryByCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryByCategoryName.isPresent()) {
            throw new CategoryNameExistException(category.getCategoryName());
        } else {
            Category categorySave = new Category();
            categorySave.setCategoryName(category.getCategoryName());
            return categoryRepository.save(categorySave);
        }
    }

    @Override
    public Category saveSubCategory(Category category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> updateCategory(Category category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCategory(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

}
