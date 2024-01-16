///PromotionController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Promotion;
import com.magazin.main.services.PromotionService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping("/promotions/{id}")
    public ResponseEntity<Promotion> getPromotion(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(promotionService.getPromotion(id));
    }
}