package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.service.CategoryService;

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

}
