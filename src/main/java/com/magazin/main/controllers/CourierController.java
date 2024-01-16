///CourierController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Courier;
import com.magazin.main.entities.Order;
import com.magazin.main.services.CourierService;
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
    public ResponseEntity<Courier> getCourier(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(courierService.getCourier(id));
    }

    @GetMapping("/couriers")
    public ResponseEntity<List<Courier>> getAllCouriers() {
        return ResponseEntity.ok().body(courierService.getAllCouriers());
    }
}