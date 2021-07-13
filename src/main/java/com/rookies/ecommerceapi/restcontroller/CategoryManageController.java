package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/category")
public class CategoryManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryManageController.class);

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryManageController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/parent")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);

        ResponseDto responseDto = categoryService.saveCategory(categoryRequest);

        responseDto.setData(modelMapper.map(responseDto.getData(), CategoryDto.class));

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/sub")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> saveSubCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);

        ResponseDto responseDto = categoryService.saveSubCategory(categoryRequest);

        responseDto.setData(modelMapper.map(responseDto.getData(), CategoryDto.class));

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category categoryRequest = modelMapper.map(categoryDto, Category.class);
        return ResponseEntity.ok(categoryService.updateCategory(categoryRequest));
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
