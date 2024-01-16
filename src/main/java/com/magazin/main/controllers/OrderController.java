///OrderController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Order;
import com.magazin.main.services.OrderService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(orderService.getOrder(id));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("aid") UUID adminId) {
        return ResponseEntity.ok().body(orderService.getAllOrders(adminId));
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> setOrder(
            @RequestParam("uid") UUID userId,
            @RequestParam("pid") UUID productId,
            @RequestParam("qty") int quantity,
            @RequestParam("courier") UUID courierId
    ){
//        return ResponseEntity.ok().body((userId).toString());
        return ResponseEntity.ok().body(orderService.setOrder(userId,productId,quantity,courierId));
    }

    @PostMapping("/deleteOrder")
    public ResponseEntity<Void> eraseOrder(
            @RequestParam("uid") UUID userId,
            @RequestParam("oid") UUID orderId
    ){
        orderService.eraseOrder(userId,orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/deleteOrder")
    public ResponseEntity<Void> adminEraseOrder(
            @RequestParam("uid") UUID userId,
            @RequestParam("oid") UUID orderId
    ){
        orderService.adminEraseOrder(userId,orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}