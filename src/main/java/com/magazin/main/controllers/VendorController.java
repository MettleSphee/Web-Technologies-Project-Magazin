///VendorController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Vendor;
import com.magazin.main.services.VendorService;
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
public class VendorController {
    private final VendorService vendorService;

    @GetMapping("/vendors/{id}")
    @Operation(summary = "getVendor(UUID)", description = "GETs a vendor based on its own UUID")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<Vendor> getVendor(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(vendorService.getVendor(id));
    }
}