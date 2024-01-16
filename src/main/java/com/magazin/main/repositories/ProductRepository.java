///ProductRepository.java
package com.magazin.main.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magazin.main.entities.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    Product findProductById(@Param("id")  UUID id);
    List<Product> findAll();
}