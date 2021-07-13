package com.rookies.ecommerceapi.restcontroller;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookies.ecommerceapi.dto.CategoryDto;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/parent")
    public List<CategoryDto> retrieveParentCategory() {
        return categoryService.retrieveParentCategory().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/sub/{parentId}")
    public List<CategoryDto> retrieveSubCategory(@PathVariable("parentId") Integer parentId) {
        return categoryService.retrieveSubCategory(parentId).stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
