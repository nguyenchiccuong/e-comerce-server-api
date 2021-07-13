package com.rookies.ecommerceapi.service;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Category;

public interface CategoryService {
    public ResponseDto retrieveParentCategory();

    public ResponseDto retrieveSubCategory(Integer parentId);

    public ResponseDto saveCategory(Category category);

    public ResponseDto saveSubCategory(Category category);

    public ResponseDto updateCategory(Category category);

    public ResponseDto deleteCategory(Integer id);
}
