///CourierController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Courier;
import com.magazin.main.entities.Order;
import com.magazin.main.services.CourierService;
import io.swagger.v3.oas.annotations.Operation;
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
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/couriers/{id}")
    @Operation(summary = "getCourier(UUID)", description = "GETs a courier based on its own UUID")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<Courier> getCourier(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(courierService.getCourier(id));
    }

    @GetMapping("/couriers")
    @Operation(summary = "getAllCouriers()", description = "GETs all couriers from the couriers table")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found")
    })
    public ResponseEntity<List<Courier>> getAllCouriers() {
        return ResponseEntity.ok().body(courierService.getAllCouriers());
    }
}