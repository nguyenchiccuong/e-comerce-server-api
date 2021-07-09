package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.rookies.ecommerceapi.dto.CategoryDto;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/category")
public class CategoryManageController {

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryManageController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/parent")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public CategoryDto saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        Category category = categoryService.saveCategory(categoryRequest);
        return modelMapper.map(category, CategoryDto.class);
    }

    @PostMapping("/sub")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public CategoryDto saveSubCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        Category category = categoryService.saveSubCategory(categoryRequest);
        return modelMapper.map(category, CategoryDto.class);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        return categoryService.updateCategory(categoryRequest);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
