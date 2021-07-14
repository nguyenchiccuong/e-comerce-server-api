package com.rookies.ecommerceapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import com.rookies.ecommerceapi.constant.ErrorCode;
import com.rookies.ecommerceapi.constant.SuccessCode;
import com.rookies.ecommerceapi.dto.CategoryDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.repository.ProductRepository;
import com.rookies.ecommerceapi.service.CategoryService;
import com.rookies.ecommerceapi.exception.CategoryNameExistException;
import com.rookies.ecommerceapi.exception.CategoryExistInProductException;
import com.rookies.ecommerceapi.exception.CategoryIdNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository,
            ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto retrieveParentCategory() {
        ResponseDto responseDto = new ResponseDto();
        List<Category> parentCategory = categoryRepository.findByCategoryIsNull();
        List<CategoryDto> categoriesDto = parentCategory.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        responseDto.setData(categoriesDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }

    @Override
    public ResponseDto retrieveSubCategory(Integer parentId) {
        ResponseDto responseDto = new ResponseDto();
        List<Category> subCategory = categoryRepository.findSubCategoryByParentCategoryId(parentId);
        List<CategoryDto> categoriesDto = subCategory.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        responseDto.setData(categoriesDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }

    @Override
    public ResponseDto saveCategory(Category category) {
        ResponseDto responseDto = new ResponseDto();

        Optional<Category> categoryByCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryByCategoryName.isPresent()) {
            throw new CategoryNameExistException(ErrorCode.ERR_CATEGORY_NAME_EXIST);
        } else {
            Category categorySave = new Category();
            categorySave.setCategoryName(category.getCategoryName());
            categorySave = categoryRepository.save(categorySave);

            responseDto.setSuccessCode(SuccessCode.SUCCESS);
            responseDto.setData(categorySave);
            return responseDto;
        }
    }

    @Override
    public ResponseDto saveSubCategory(Category category) {
        ResponseDto responseDto = new ResponseDto();

        if (category.getId() == null) {
            throw new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND);
        }

        // check if not null and id is a parent category
        Category categoryById = categoryRepository.findByIdAndCategoryIsNull(category.getId())
                .orElseThrow(() -> new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND));

        Optional<Category> categoryByCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryByCategoryName.isPresent()) {
            throw new CategoryNameExistException(ErrorCode.ERR_CATEGORY_NAME_EXIST);
        }

        Category categorySave = new Category();
        categorySave.setCategory(categoryById);
        categorySave.setCategoryName(category.getCategoryName());
        categorySave = categoryRepository.save(categorySave);

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        responseDto.setData(categorySave);
        return responseDto;

    }

    @Override
    public ResponseDto updateCategory(Category category) {
        ResponseDto responseDto = new ResponseDto();

        if (category.getId() == null) {
            throw new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND);
        }

        Category categoryById = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND));

        if (categoryById.getCategoryName().equals(category.getCategoryName())) {
            throw new CategoryNameExistException(ErrorCode.ERR_CATEGORY_NAME_EXIST);
        }

        Optional<Category> categoryByCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryByCategoryName.isPresent()) {
            throw new CategoryNameExistException(ErrorCode.ERR_CATEGORY_NAME_EXIST);
        }

        Category categoryUpdate = categoryById;
        categoryUpdate.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryUpdate);

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;

    }

    @Override
    public ResponseDto deleteCategory(Integer id) {
        ResponseDto responseDto = new ResponseDto();

        boolean exist = categoryRepository.existsById(id);
        if (!exist) {
            throw new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND);
        }

        List<Product> listProductCategoryId = productRepository.findByCategoryId(id);
        if (listProductCategoryId.size() > 0) {
            throw new CategoryExistInProductException(ErrorCode.ERR_CATEGORY_EXIST_IN_PRODUCT);
        }

        categoryRepository.deleteById(id);

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;

    }

}
