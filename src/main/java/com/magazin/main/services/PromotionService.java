///PromotionService.java
package com.magazin.main.services;

import com.magazin.main.entities.Promotion;
import com.magazin.main.repositories.PromotionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository repository;
    public Promotion getPromotion(UUID id) {
        return repository.findById(id).get();
    }
}