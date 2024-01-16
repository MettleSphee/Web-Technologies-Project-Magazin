package com.magazin.main.controller;

import com.magazin.main.entities.*;
import com.magazin.main.repositories.*;
import com.magazin.main.services.*;
import com.magazin.main.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.Optional;
import com.magazin.main.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;
    private Product product;
    @Mock
    private ProductService productService;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private List<Category> categoryList;


    @BeforeEach
    void init(){
        product = new Product(
                UUID.fromString("ba66cefd-59ce-4575-83b9-ff42acfaed9e"),
                "laptop smecher","are intelu ala bun si duce si fortnait",
                UUID.fromString("ca07c25c-d93d-4a5f-9e0f-61bf4c8077fd"),
                200.0,
                2,
                UUID.fromString("0dad31dd-6bef-4591-91af-620891541385"),
                UUID.fromString("3c232a79-50d2-4401-9ba4-2677d591b75f")
        );


    }

    @Test
    void getAllCategoriesTest(){
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        List<Category> categories = categoryService.getAllCategories();
        assertFalse(categories.isEmpty());
        assertEquals(1,categories.size());
        System.out.println(category);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getSingleCategoryTest(){
        Product firstProduct = productService.getAllProducts().get(1);

        when(categoryRepository.findCategoryById(firstProduct.getCategory_id())).thenReturn(category);
        Category testCategory = categoryRepository.findCategoryById(firstProduct.getCategory_id());

        assertNotNull(testCategory);
        assertEquals(testCategory.getId(), firstProduct.getCategory_id());

        verify(categoryRepository, times(1)).findCategoryById(firstProduct.getCategory_id());
    }

}
