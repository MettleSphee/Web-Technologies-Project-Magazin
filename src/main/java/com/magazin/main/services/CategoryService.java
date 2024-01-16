package com.magazin.main.services;

import com.magazin.main.entities.Category;
import com.magazin.main.entities.Product;
import com.magazin.main.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    public Category getCategory(UUID id) {
        return repository.findById(id).get();
    }

    public List<Category> getAllCategories(){return repository.findAll();}
}
