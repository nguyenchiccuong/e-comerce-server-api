package com.rookies.ecommerceapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rookies.ecommerceapi.constant.ErrorCode;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.repository.ProductRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void saveSubCategoryDuplicateName() {
        assertNotNull(categoryRepository);
        // Given
        Category category = new Category();
        category.setCategoryName("test");
        when(categoryRepository.findByCategoryName("test")).thenReturn(Optional.of(category));

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            categoryService.saveCategory(category);
        });

        // Then
        assertEquals(ErrorCode.ERR_CATEGORY_NAME_EXIST, exception.getMessage());
    }

    @Test
    public void updateCategoryIdNotFound() {

        assertNotNull(categoryRepository);
        // Given
        Category category = new Category();
        category.setId(4);
        category.setCategoryName("test");

        when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            categoryService.updateCategory(category);
        });

        // Then
        assertEquals(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND, exception.getMessage());
    }
}
