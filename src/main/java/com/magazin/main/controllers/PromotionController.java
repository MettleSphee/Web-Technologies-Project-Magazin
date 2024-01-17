///PromotionController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Promotion;
import com.magazin.main.services.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "getPromotion(UUID)", description = "GETs a promotion based on its own UUID")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<Promotion> getPromotion(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(promotionService.getPromotion(id));
    }
}