///VendorController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Vendor;
import com.magazin.main.services.VendorService;
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
    public ResponseEntity<Vendor> getVendor(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(vendorService.getVendor(id));
    }
}