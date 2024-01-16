package com.magazin.main.controllers;

import com.magazin.main.entities.Order;
import com.magazin.main.entities.User;
import com.magazin.main.services.UserService;
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
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam("aid") UUID adminId) {

        return ResponseEntity.ok().body(userService.getAllUsers(adminId));
    }
}
