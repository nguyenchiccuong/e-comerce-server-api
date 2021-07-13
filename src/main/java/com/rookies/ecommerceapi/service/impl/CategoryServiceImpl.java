package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.rookies.ecommerceapi.constant.ErrorCode;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.repository.ProductRepository;
import com.rookies.ecommerceapi.service.CategoryService;
import com.rookies.ecommerceapi.exception.CategoryNameExistException;
import com.rookies.ecommerceapi.payload.respone.MessageResponse;
import com.rookies.ecommerceapi.exception.CategoryExistInProductException;
import com.rookies.ecommerceapi.exception.CategoryIdNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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
            throw new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND);
        }

        // check if not null and id is a parent category
        Category categoryById = categoryRepository.findByIdAndCategoryIsNull(category.getId())
                .orElseThrow(() -> new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND));

        Optional<Category> categoryByCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryByCategoryName.isPresent()) {
            throw new CategoryNameExistException(category.getCategoryName());
        }

        Category categorySave = new Category();
        categorySave.setCategory(categoryById);
        categorySave.setCategoryName(category.getCategoryName());
        return categoryRepository.save(categorySave);

    }

    @Override
    public ResponseEntity<?> updateCategory(Category category) {
        if (category.getId() == null) {
            throw new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND);
        }

        Category categoryById = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND));

        if (categoryById.getCategoryName().equals(category.getCategoryName())) {
            throw new CategoryNameExistException(category.getCategoryName());
        }

        Optional<Category> categoryByCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryByCategoryName.isPresent()) {
            throw new CategoryNameExistException(category.getCategoryName());
        }

        Category categoryUpdate = categoryById;
        categoryUpdate.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryUpdate);
        return ResponseEntity.ok(new MessageResponse("Update success"));

    }

    @Override
    public ResponseEntity<?> deleteCategory(Integer id) {
        boolean exist = categoryRepository.existsById(id);
        if (!exist) {
            throw new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND);
        }

        List<Product> listProductCategoryId = productRepository.findByCategoryId(id);
        if (listProductCategoryId.size() > 0) {
            throw new CategoryExistInProductException(ErrorCode.ERR_CATEGORY_EXIST_IN_PRODUCT);
        }

        categoryRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Delete success"));

    }

}
