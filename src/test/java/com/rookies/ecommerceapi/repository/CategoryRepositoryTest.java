package com.rookies.ecommerceapi.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rookies.ecommerceapi.entity.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void CategoryTest() {
        Category parentCategory = new Category();
        parentCategory.setCategoryName("test");
        assertNotNull(categoryRepository.save(parentCategory));

        parentCategory = categoryRepository.findByCategoryName("test").get();

        Category subCategory = new Category();
        subCategory.setCategoryName("sub");
        subCategory.setCategory(parentCategory);
        assertNotNull(categoryRepository.save(subCategory));

        subCategory = categoryRepository.findByCategoryName("sub").get();

        subCategory.setCategoryName("bum");
        assertNotNull(categoryRepository.save(subCategory));

        categoryRepository.deleteById(parentCategory.getId());
        assertTrue(!categoryRepository.findByCategoryName("test").isPresent());
    }
}
