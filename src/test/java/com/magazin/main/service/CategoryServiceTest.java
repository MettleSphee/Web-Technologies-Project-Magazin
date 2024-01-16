package com.magazin.main.service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;
    private Product product;
    private List<Product> productList;
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
        Category category1 = new Category(
                UUID.fromString("ca07c25c-d93d-4a5f-9e0f-61bf4c8077fd"),
                "IT si Tehnologie","it"
        );
        Category category2 = new Category(
                UUID.fromString("4431839d-6280-4db9-8513-6f3799d92383"),
                "Mancare","food"
        );
        productList = new ArrayList<>();
        productList.add(product);
        categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);
    }

    @Test
    void getAllCategoriesTest(){
//        System.out.println(categoryList);
//        when(categoryRepository.findAll()).thenReturn(List.of(category));
        given(categoryRepository.findAll()).willReturn(categoryList);
        categoryList = categoryService.getAllCategories();
        assertFalse(categoryList.isEmpty());
        assertEquals(2,categoryList.size());
//        System.out.println(categoryList);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getSingleCategoryTest(){
        Product firstProduct = productList.get(0);

        UUID testCatId = firstProduct.getCategory_id();
        System.out.println(testCatId);

        given(categoryService.getCategory(testCatId)).willReturn(category);
        Category testCategory = categoryService.getCategory(testCatId);

        assertNotNull(testCategory);
        assertEquals(testCategory.getId(), firstProduct.getCategory_id());

        verify(categoryRepository, times(1)).findCategoryById(firstProduct.getCategory_id());
    }

}
