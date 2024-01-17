package com.magazin.main.controllers;

import com.magazin.main.entities.Category;
import com.magazin.main.entities.Product;
import com.magazin.main.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/categories/{id}")
    @Operation(summary = "getCategory(UUID)", description = "GETs a category based on its own UUID")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<Category> getCategory(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }

    @GetMapping("/categories")
    @Operation(summary = "getAllCategories()", description = "GETs all categories from the categories table")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found")
    })
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }
}
