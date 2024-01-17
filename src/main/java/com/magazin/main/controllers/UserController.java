package com.magazin.main.controllers;

import com.magazin.main.entities.Order;
import com.magazin.main.entities.User;
import com.magazin.main.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    @Operation(summary = "getUser(UUID)", description = "GETs an user based on its own UUID\nThis value should only be known by the user with the UUID to be accessed, or by administrators.")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @GetMapping("/users")
    @Operation(summary = "getAllUsers(UUID)", description = "GETs all the users in the users table, providing the UUID of an administrator user.\nThis should only be accessed by administrators.")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Returned successfully"),
            @ApiResponse(responseCode = "4xx",description = "Table/Database not found"),
            @ApiResponse(responseCode = "500",description = "UUID not found")
    })
    public ResponseEntity<List<User>> getAllUsers(@RequestParam("aid") UUID adminId) {

        return ResponseEntity.ok().body(userService.getAllUsers(adminId));
    }
}
