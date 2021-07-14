package com.rookies.ecommerceapi.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/parent")
    public ResponseEntity<ResponseDto> retrieveParentCategory() {
        return ResponseEntity.ok(categoryService.retrieveParentCategory());
    }

    @GetMapping("/sub/{parentId}")
    public ResponseEntity<ResponseDto> retrieveSubCategory(@PathVariable("parentId") Integer parentId) {
        return ResponseEntity.ok(categoryService.retrieveSubCategory(parentId));
    }
}
