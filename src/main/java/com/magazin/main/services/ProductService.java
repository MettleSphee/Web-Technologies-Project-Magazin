///ProductService.java
package com.magazin.main.services;

import com.magazin.main.entities.Product;
import com.magazin.main.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    public Product getProduct(UUID id) {
        return repository.findById(id).get();
    }
    public List<Product> getAllProducts(){return repository.findAll();}
}