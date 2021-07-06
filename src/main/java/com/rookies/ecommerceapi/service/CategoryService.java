package com.rookies.ecommerceapi.service;

import java.util.List;
import java.util.Optional;

import com.rookies.ecommerceapi.entity.Category;

public interface CategoryService {
    public List<Category> retrieveParentCategory();

    public List<Category> retrieveSubCategory(Integer parentId);
}
