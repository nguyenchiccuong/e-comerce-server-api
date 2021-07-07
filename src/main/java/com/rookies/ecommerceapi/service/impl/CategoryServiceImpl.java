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
import com.rookies.ecommerceapi.payload.respone.MessageResponse;
import com.rookies.ecommerceapi.exception.CategoryIdNotFoundException;

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
        if (category.getId() == null) {
            throw new CategoryIdNotFoundException(category.getId());
        }

        // check if not null and id is a parent category
        Optional<Category> categoryById = categoryRepository.findByIdAndCategoryIsNull(category.getId());
        if (!categoryById.isPresent()) {
            throw new CategoryIdNotFoundException(category.getId());
        }

        Optional<Category> categoryByCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryByCategoryName.isPresent()) {
            throw new CategoryNameExistException(category.getCategoryName());
        }

        Category categorySave = new Category();
        categorySave.setCategory(categoryById.get());
        categorySave.setCategoryName(category.getCategoryName());
        return categoryRepository.save(categorySave);

    }

    @Override
    public ResponseEntity<?> updateCategory(Category category) {
        if (category.getId() == null) {
            throw new CategoryIdNotFoundException(category.getId());
        }

        Optional<Category> categoryById = categoryRepository.findById(category.getId());
        if (!categoryById.isPresent()) {
            throw new CategoryIdNotFoundException(category.getId());
        }
        if (categoryById.get().getCategoryName().equals(category.getCategoryName())) {
            throw new CategoryNameExistException(category.getCategoryName());
        }

        Optional<Category> categoryByCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryByCategoryName.isPresent()) {
            throw new CategoryNameExistException(category.getCategoryName());
        }

        Category categoryUpdate = categoryById.get();
        categoryUpdate.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryUpdate);
        return ResponseEntity.ok(new MessageResponse("update success"));

    }

    @Override
    public ResponseEntity<?> deleteCategory(Integer id) {
        if (id == null) {
            throw new CategoryIdNotFoundException(id);
        }
        return null;
    }

}
