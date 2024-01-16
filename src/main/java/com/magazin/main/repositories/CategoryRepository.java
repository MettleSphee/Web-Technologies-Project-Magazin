package com.magazin.main.repositories;

import java.util.*;

import com.magazin.main.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.magazin.main.entities.Category;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>{
    Category findCategoryById(@Param("id")  UUID id);
    List<Category> findAll();
}
