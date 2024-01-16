///PromotionRepository.java
package com.magazin.main.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magazin.main.entities.Promotion;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, UUID>{
    Promotion findPromotionById(@Param("id")  UUID id);
}