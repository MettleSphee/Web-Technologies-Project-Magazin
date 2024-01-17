///OrderController.java
package com.magazin.main.controllers;

import com.magazin.main.entities.Order;
import com.magazin.main.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "getOrder(UUID)", description = "GETs an order based on its own UUID\nThis value should only be known by the user with the UUID to be accessed, or by administrators.")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<Order> getOrder(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(orderService.getOrder(id));
    }

    @GetMapping("/orders")
    @Operation(summary = "getAllOrders(UUID)", description = "GETs all the orders in the orders table, providing the UUID of an administrator user.\nThis should only be accessed by administrators.")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("aid") UUID adminId) {
        return ResponseEntity.ok().body(orderService.getAllOrders(adminId));
    }

    @PostMapping("/createOrder")
    @Operation(summary = "setOrder(UserUUID,ProductUUID,Quantity(int),CourierUUID)", description = "(POST) Creates an order using the provided UserID, ProductID, Quantity and CourierID.\nReturns the full order, along with the OrderID and calculated total price.\nThis order should only be created by the user with the UUID to be accessed, or by administrators exclusively for testing.")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Created successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "One of the specified UUIDs was not found")
    })
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
    @Operation(summary = "eraseOrder(UserUUID,OrderUUID)", description = "(POST) Deletes an order using the provided UserID and OrderID.\nReturns the full order, along with the OrderID and calculated total price.\nThis order should only be created by the user with the UUID to be accessed, or by administrators exclusively for testing.")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Created successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "One of the specified UUIDs was not found")
    })
    public ResponseEntity<Void> eraseOrder(
            @RequestParam("uid") UUID userId,
            @RequestParam("oid") UUID orderId
    ){
        orderService.eraseOrder(userId,orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/deleteOrder")
    @Operation(summary = "adminEraseOrder(UserUUID,OrderUUID)", description = "(POST) Deletes an order using the provided OrderID.\nRequires the administrator UUID to be specified first.\nReturns the full order, along with the OrderID and calculated total price.\nThis order should only be created by the user with the UUID to be accessed, or by administrators exclusively for testing.")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Created successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "One of the specified UUIDs was not found")
    })
    public ResponseEntity<Void> adminEraseOrder(
            @RequestParam("uid") UUID userId,
            @RequestParam("oid") UUID orderId
    ){
        orderService.adminEraseOrder(userId,orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}