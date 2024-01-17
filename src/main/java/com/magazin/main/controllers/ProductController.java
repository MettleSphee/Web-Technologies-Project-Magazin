///ProductController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Product;
import com.magazin.main.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Validated
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    @Operation(summary = "getProduct(UUID)", description = "GETs a product based on its own UUID")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<Product> getProduct(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @GetMapping("/products")
    @Operation(summary = "getAllProducts()", description = "GETs all products from the products table")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found")
    })
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
}